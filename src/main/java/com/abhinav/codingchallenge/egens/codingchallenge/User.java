package com.abhinav.codingchallenge.egens.codingchallenge;

import java.util.Objects;

import com.mongodb.BasicDBObject;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String dateCreated;
    private Company company;
    private String profilePic;

    public User(BasicDBObject dbObject) {
        this.id = dbObject.getString("id");
        this.firstName = dbObject.getString("firstName");
        this.lastName = dbObject.getString("lastName");
        this.email = dbObject.getString("email");
        this.dateCreated = dbObject.getString("dateCreated");
        if (dbObject.get("company") != null) {
            Company company = new Company();
            BasicDBObject companyDbObject = (BasicDBObject) dbObject.get("company");
            company.setName(companyDbObject.getString("name"));
            company.setWebsite(companyDbObject.getString("website"));
            this.company = company;
        }

        if (dbObject.get("address") != null) {
            Address addr = new Address();
            BasicDBObject addressDbObject = (BasicDBObject) dbObject.get("address");
            addr.setCity(addressDbObject.getString("city"));
            addr.setCountry(addressDbObject.getString("country"));
            addr.setState(addressDbObject.getString("state"));
            addr.setZip(addressDbObject.getString("zip"));
            addr.setStreet(addressDbObject.getString("street"));
            this.address = addr;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}

class Address {

    private String street;
    private String city;
    private String zip;
    private String state;
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        Address otherAddr = (Address) obj;
        if (Objects.equals(this.city, otherAddr.getCity()) &&
                Objects.equals(this.country, otherAddr.getCountry()) &&
                Objects.equals(this.state, otherAddr.getState()) &&
                Objects.equals(this.street, otherAddr.getStreet()) &&
                Objects.equals(this.zip, otherAddr.getZip())) {
            return true;
        }

        return false;
    }
}

class Company {

    private String name;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object obj) {
        Company otherCompany = (Company) obj;
        return Objects.equals(this.name, otherCompany.getName()) &&
                Objects.equals(this.website, otherCompany.getWebsite());
    }
}
