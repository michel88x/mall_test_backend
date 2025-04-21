package com.michel.mall_test.extra.filters;

import com.michel.mall_test.entity.User;
import com.michel.mall_test.repository.TokenRepository;
import com.michel.mall_test.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    /// This service to extract the data from the token
    private final JwtService jwtService;
    /// Predefined service
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }


    @Override
    protected void doFilterInternal(
            ///This is the API request object, and we can extract info from it (Must not be null)
            @NonNull HttpServletRequest request,
            /// To control the response for example adding a Header to it (Must not be null)
            @NonNull HttpServletResponse response,
            /// To call the next filter in the chain (Must not be null)
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        /// Extract the authorization value from the header
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        /// Check if there is no token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            /// Move to the next filter
            filterChain.doFilter(request, response);
            return;
        }
        /// Extracting the JWTToken
        jwtToken = authHeader.substring(7);
        /// Extract the user Email from the token
        userEmail = jwtService.extractUsername(jwtToken);
        /// Check if the userEmail is existed and if the user is not yet authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            /// Get the user
            /// userDetailsService is defined as a Bean in ApplicationConfig
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            /// Check if saved token in the Database is valid
            boolean isTokenValid = tokenRepository.findByToken(jwtToken).map(
                    t -> !t.isExpired() && !t.isRevoked()) .orElse(false);
            /// Check if there is a User existed in the database with this token
            Optional<User> user = jwtService.getUserByToken(authHeader);
            /// Check if the token is still valid
            if (jwtService.isTokenValid(jwtToken, userDetails) && isTokenValid && user.isPresent()) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                /// Update the security context holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

