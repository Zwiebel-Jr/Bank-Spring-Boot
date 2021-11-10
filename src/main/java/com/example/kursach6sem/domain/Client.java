package com.example.kursach6sem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class    Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    private String gender;
    @Column(name = "passport_serial_number")
    private String passportSerialNumber;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "passport_issuer")
    private String passportIssuer;
    @Column(name = "passport_issue_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date passportIssueDate;
    @Column(name = "passport_identification_number")
    private String passportIdentificationNumber;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
   // @ManyToOne
   // @JoinColumn(name = "city_id", nullable = false)
   // private City cityOfResidence;
    @Column(name = "address_of_residence")
    private String addressOfResidence;
    @Column(name = "address_of_registry")
    private String addressOfRegistry;
   // @ManyToOne
   // @JoinColumn(name = "marital_status_id", nullable = false)
   // private MaritalStatus maritalStatus;
  //  @ManyToOne
   // @JoinColumn(name = "citizenship_id", nullable = false)
   // private Citizenship citizenship;
   // @ManyToOne
   // @JoinColumn(name = "disability_id", nullable = false)
   // private Disability disability;
   // private Boolean pensioner;
   // @Column(name = "duty_bound")
   // private Boolean dutyBound;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Credit> credits;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Debit> debits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassportSerialNumber() {
        return passportSerialNumber;
    }

    public void setPassportSerialNumber(String passportSerialNumber) {
        this.passportSerialNumber = passportSerialNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportIssuer() {
        return passportIssuer;
    }

    public void setPassportIssuer(String passportIssuer) {
        this.passportIssuer = passportIssuer;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIdentificationNumber() {
        return passportIdentificationNumber;
    }

    public void setPassportIdentificationNumber(String passportIdentificationNumber) {
        this.passportIdentificationNumber = passportIdentificationNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

 //   public City getCityOfResidence() {
 //       return cityOfResidence;
  //  }

  //  public void setCityOfResidence(City cityOfResidence) {
  //      this.cityOfResidence = cityOfResidence;
  //  }

    public String getAddressOfResidence() {
        return addressOfResidence;
    }

    public void setAddressOfResidence(String addressOfResidence) {
        this.addressOfResidence = addressOfResidence;
    }

    public String getAddressOfRegistry() {
        return addressOfRegistry;
    }

    public void setAddressOfRegistry(String addressOfRegistry) {
        this.addressOfRegistry = addressOfRegistry;
    }

  //  public MaritalStatus getMaritalStatus() {
   //     return maritalStatus;
  //  }

  //  public void setMaritalStatus(MaritalStatus maritalStatus) {
    //    this.maritalStatus = maritalStatus;
  //  }

  //  public Citizenship getCitizenship() {
    //    return citizenship;
   // }

  //  public void setCitizenship(Citizenship citizenship) {
    //    this.citizenship = citizenship;
    //}

  //  public Disability getDisability() {
    //    return disability;
    //}

   // public void setDisability(Disability disability) {
    //    this.disability = disability;
   // }

   // public Boolean getPensioner() {
    //    return pensioner;
    //}

    //public void setPensioner(Boolean pensioner) {
      //  this.pensioner = pensioner;
    //}

   // public Boolean getDutyBound() {
     //   return dutyBound;
    //}

    //public void setDutyBound(Boolean dutyBound) {
    //    this.dutyBound = dutyBound;
    //}

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Debit> getDebits() {
        return debits;
    }

    public void setDebits(List<Debit> debits) {
        this.debits = debits;
    }
}
