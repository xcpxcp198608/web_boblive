package com.wiatec.boblive.voucher;

public class VoucherInfo {
    /**
     * auth : N
     * error : Successful
     * result : 0
     * user : {"address":"cc","birthday":"2017-11-16","city":"ss","country":"CZ","email":"000100000000000005@ecoupon.pro","firstname":"patrick","gender":"M","kycCheckDone":"N","nickname":"vps_47","surname":"xu","zip":""}
     * voucher : {"amount":100,"currency":"EUR","expiration":null,"id":102691244838,"owner":"000100000000000005"}
     */

    private String auth;
    private String error;
    private int result;
    private UserBean user;
    private VoucherBean voucher;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public VoucherBean getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherBean voucher) {
        this.voucher = voucher;
    }

    public static class UserBean {
        /**
         * address : cc
         * birthday : 2017-11-16
         * city : ss
         * country : CZ
         * email : 000100000000000005@ecoupon.pro
         * firstname : patrick
         * gender : M
         * kycCheckDone : N
         * nickname : vps_47
         * surname : xu
         * zip :
         */

        private String address;
        private String birthday;
        private String city;
        private String country;
        private String email;
        private String firstname;
        private String gender;
        private String kycCheckDone;
        private String nickname;
        private String surname;
        private String zip;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getKycCheckDone() {
            return kycCheckDone;
        }

        public void setKycCheckDone(String kycCheckDone) {
            this.kycCheckDone = kycCheckDone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "address='" + address + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", email='" + email + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", gender='" + gender + '\'' +
                    ", kycCheckDone='" + kycCheckDone + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", surname='" + surname + '\'' +
                    ", zip='" + zip + '\'' +
                    '}';
        }
    }

    public static class VoucherBean {
        /**
         * amount : 100
         * currency : EUR
         * expiration : null
         * id : 102691244838
         * owner : 000100000000000005
         */

        private int amount;
        private String currency;
        private String expiration;
        private long id;
        private String owner;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        @Override
        public String toString() {
            return "VoucherBean{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    ", expiration='" + expiration + '\'' +
                    ", id=" + id +
                    ", owner='" + owner + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VoucherInfo{" +
                "auth='" + auth + '\'' +
                ", error='" + error + '\'' +
                ", result=" + result +
                ", user=" + user +
                ", voucher=" + voucher +
                '}';
    }
}