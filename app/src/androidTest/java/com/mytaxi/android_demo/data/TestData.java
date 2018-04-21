package com.mytaxi.android_demo.data;

public class TestData {
    public enum UserLoginData {
        validUser("whiteelephant261", "video1");

        private String username;
        private String password;

        UserLoginData(final String username, final String password) {
            this.username = username;
            this.password = password;
        }

        public String getUserName() {
            return this.username;
        }
        public String getPassword() {
            return this.password;
        }
    }
    public enum SearchData {
        SearchCriteria("sa","Sarah Friedrich");
        private String searchData;
        private String fullName;

        SearchData(final String searchData, final String fullName) {
            this.searchData = searchData;
            this.fullName = fullName;
        }
        public String getSearchData() {
            return this.searchData;
        }
        public String getFullName() {
            return this.fullName;
        }
    }
    public enum DriverProfile {
        SarahDriver("Sarah Friedrich","2297 bahnhofstraße", "2013-03-05", "tel:0174-8819231");
        private String name;
        private String location;
        private String date;
        private String phoneNum;

        DriverProfile(final String name, final String location, final String date, final String phoneNum) {
            this.name = name;
            this.location = location;
            this.date = date;
            this.phoneNum = phoneNum;
        }
        public String getDriverName() {
            return this.name;
        }
        public String getDriverLocation() {
            return this.location;
        }
        public String getDriverDate() {
            return this.date;
        }
        public String getDriverPhoneNum() {
            return this.phoneNum;
        }
    }
}

