package com.michel.mall_test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.michel.mall_test.extra.enums.Gender;
import com.michel.mall_test.extra.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_users")
public class User extends BaseEntity implements UserDetails {

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column()
    @ColumnDefault(value = "false")
    private Boolean emailVerified;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column
    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
    private Gender gender;

    @Column
    @Lob
    private String fcmToken;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_VALUES)
    private Role role;

    @Column
    @ColumnDefault(value = "0")
    private Integer points;

    @Column
    private String pass;

    //Newly added
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReferralCode referralCode;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductRate> rates;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FavouriteProduct> favourites;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserVerificationCode verificationCode;

    public User() {}

    public User(String name,
            String email,
            String phoneNumber,
            Boolean phoneNumberVerified,
            Date birthdate,
            Gender gender,
            String fcmToken,
            Role role,
            Integer points,
                String pass,
                ReferralCode referralCode,
                List<Address> addresses,
                List<ProductRate> rates,
                List<FavouriteProduct> favourites,
                Cart cart,
                List<Order> orders,
                UserVerificationCode verificationCode) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emailVerified = phoneNumberVerified;
        this.birthdate = birthdate;
        this.gender = gender;
        this.fcmToken = fcmToken;
        this.role = role;
        this.points = points;
        this.pass = pass;
        this.referralCode = referralCode;
        this.addresses = addresses;
        this.rates = rates;
        this.favourites = favourites;
        this.cart = cart;
        this.orders = orders;
        this.verificationCode = verificationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ReferralCode getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(ReferralCode referralCode) {
        this.referralCode = referralCode;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<ProductRate> getRates() {
        return rates;
    }

    public void setRates(List<ProductRate> rates) {
        this.rates = rates;
    }

    public List<FavouriteProduct> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<FavouriteProduct> favourites) {
        this.favourites = favourites;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public UserVerificationCode getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(UserVerificationCode verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static final class UserBuilder {
        private String name;
        private String email;
        private String phoneNumber;
        private Boolean emailVerified;
        private Date birthdate;
        private Gender gender;
        private String fcmToken;
        private Role role;
        private Integer points;
        private String pass;
        private ReferralCode referralCode;
        private List<Address> addresses;
        private List<ProductRate> rates;
        private List<FavouriteProduct> favourites;
        private Cart cart;
        private List<Order> orders;
        private UserVerificationCode verificationCode;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withEmailVerified(Boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public UserBuilder withBirthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder withFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder withPoints(Integer points) {
            this.points = points;
            return this;
        }

        public UserBuilder withPass(String pass) {
            this.pass = pass;
            return this;
        }

        public UserBuilder withReferralCode(ReferralCode referralCode) {
            this.referralCode = referralCode;
            return this;
        }

        public UserBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public UserBuilder withRates(List<ProductRate> rates) {
            this.rates = rates;
            return this;
        }

        public UserBuilder withFavourites(List<FavouriteProduct> favourites) {
            this.favourites = favourites;
            return this;
        }

        public UserBuilder withCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public UserBuilder withOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public UserBuilder withUserVerificationCode(UserVerificationCode verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        public User build() {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setEmailVerified(emailVerified);
            user.setBirthdate(birthdate);
            user.setGender(gender);
            user.setFcmToken(fcmToken);
            user.setRole(role);
            user.setPoints(points);
            user.setPass(pass);
            user.setReferralCode(referralCode);
            user.setAddresses(addresses);
            user.setRates(rates);
            user.setFavourites(favourites);
            user.setCart(cart);
            user.setOrders(orders);
            user.setVerificationCode(verificationCode);
            return user;
        }
    }
}
