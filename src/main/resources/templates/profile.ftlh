<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username} ${user.id}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Почта:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>

    <div class="row no-gutters placement">

        <app-form-buttons [validToSave]="generalForm.valid && generalForm.dirty" [validToDelete]="validToDelete" (save)="save()" (delete)="delete()" (back)="back()"></app-form-buttons>

        <div class="col-12">
            <form #generalForm="ngForm" novalidate>
                <div class="form-group row no-gutters" *ngIf="client">
                    <div class="col-12">
                        <label for="firstName">Имя</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${client.firstName!''}" [(ngModel)]="client.firstName" #firstName="ngModel" required [ngClass]="{'is-invalid': (firstName.touched || firstName.dirty) && !firstName.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="firstName.errors?.required">
              Введите имя.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="lastName">Фамилия</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${client.lastName!''}" [(ngModel)]="client.lastName" #lastName="ngModel" required [ngClass]="{'is-invalid': (lastName.touched || lastName.dirty) && !lastName.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="lastName.errors?.required">
              Введите фамилию.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pl-lg-3">
                        <label for="middleName">Отчество</label>
                        <input type="text" class="form-control" id="middleName" name="middleName" value="${client.middleName!''}" [(ngModel)]="client.middleName" #middleName="ngModel" required [ngClass]="{'is-invalid': (middleName.touched || middleName.dirty) && !middleName.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="middleName.errors?.required">
              Введите отчество.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="dateOfBirth">Дата Рождения</label>
                        <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder="дд/мм/гггг"
                               value="${client.dateOfBirth!''}" [(ngModel)]="client.dateOfBirth" #dateOfBirth="ngModel" required  pattern="^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}$" [ngClass]="{'is-invalid': (dateOfBirth.touched || dateOfBirth.dirty) && !dateOfBirth.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="dateOfBirth.errors?.required">
              Введите дату рождения.
            </span>
            <span *ngIf="dateOfBirth.errors?.pattern">
              Введите дату рождения в формате(дд/мм/гггг).
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pl-lg-3">
                        <label for="gender">Пол</label>
                        <div class="row no-gutters" id="gender">
                            <div class="col-6">
                                <input type="radio" id="female" class="form-control" name="gender" value="женский" [(ngModel)]="client.gender" required #gender="ngModel" [ngClass]="{'is-invalid': (gender.touched || gender.dirty) && !gender.valid}">
                                <label for="female">Женский</label>
                            </div>
                            <div class="col-6">
                                <input type="radio" id="male" class="form-control" name="gender" value="мужской" [(ngModel)]="client.gender" required #gender="ngModel" [ngClass]="{'is-invalid': (gender.touched || gender.dirty) && !gender.valid}">
                                <label for="male">Мужской</label>
                            </div>
                        </div>
                        <span class="invalid-feedback">
            <span *ngIf="gender.errors?.required">
              Выберите пол.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="passportSerialNumber">Серия Паспорта</label>
                        <input type="text" class="form-control" id="passportSerialNumber" name="passportSerialNumber" value="${client.passportSerialNumber!''}" [(ngModel)]="client.passportSerialNumber" #passportSerialNumber="ngModel" required [ngClass]="{'is-invalid': (passportSerialNumber.touched || passportSerialNumber.dirty) && !passportSerialNumber.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="passportSerialNumber.errors?.required">
              Введите серию паспорта.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pl-lg-3">
                        <label for="passportNumber">Номер Паспорта</label>
                        <input type="text" class="form-control" id="passportNumber" name="passportNumber" placeholder="AB1234567"
                               value="${client.passportNumber!''}" [(ngModel)]="client.passportNumber" #passportNumber="ngModel" required pattern="^[A-Z]{2}\d{7}$" [ngClass]="{'is-invalid': (passportNumber.touched || passportNumber.dirty) && !passportNumber.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="passportNumber.errors?.required">
              Введите норме паспорта.
            </span>
            <span *ngIf="passportNumber.errors?.pattern">
              Введите норме паспорта в формате (2 буквы и 7 цифр).
            </span>
          </span>
                    </div>

                    <div class="col-12">
                        <label for="passportIssuer">Кем Выдан Паспорт</label>
                        <input type="text" class="form-control" id="passportIssuer" name="passportIssuer"
                               value="${client.passportIssuer!''}" [(ngModel)]="client.passportIssuer" #passportIssuer="ngModel" required [ngClass]="{'is-invalid': (passportIssuer.touched || passportIssuer.dirty) && !passportIssuer.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="passportIssuer.errors?.required">
              Ввведите кем выдан паспорт.
            </span>
          </span>
                    </div>

                    <div class="col-12">
                        <label for="passportIssueDate">Дата Выдачи Паспорта</label>
                        <input type="text" class="form-control" id="passportIssueDate" name="passportIssueDate" placeholder="дд/мм/гггг"
                               value="${client.passportIssueDate!''}" [(ngModel)]="client.passportIssueDate" #passportIssueDate="ngModel" required pattern="^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}$" [ngClass]="{'is-invalid': (passportIssueDate.touched || passportIssueDate.dirty) && !passportIssueDate.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="passportIssueDate.errors?.required">
              Введите дату выдачи паспорта.
            </span>
            <span *ngIf="passportIssueDate.errors?.pattern">
              Введите дату выдачи паспорта в формате(дд/мм/гггг).
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="passportIdentificationNumber">Идентификационный Номер Паспорта</label>
                        <input type="text" class="form-control" id="passportIdentificationNumber" name="passportIdentificationNumber"
                               placeholder="1234567AB123CD" value="${client.passportIdentificationNumber!''}" [(ngModel)]="client.passportIdentificationNumber" #passportIdentificationNumber="ngModel" required pattern="([a-zA-Z]|\d){14}" [ngClass]="{'is-invalid': (passportIdentificationNumber.touched || passportIdentificationNumber.dirty) && !passportIdentificationNumber.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="passportIdentificationNumber.errors?.required">
              Введите идентификационный номер паспорта.
            </span>
            <span *ngIf="passportIdentificationNumber.errors?.pattern">
              Введите идентификационный номер паспорта в формате (14 букв или цифр).
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="placeOfBirth">Место Рождения</label>
                        <input type="text" class="form-control" id="placeOfBirth" name="placeOfBirth" placeholder
                               value="${client.placeOfBirth!''}" [(ngModel)]="client.placeOfBirth" #placeOfBirth="ngModel" required [ngClass]="{'is-invalid': (placeOfBirth.touched || placeOfBirth.dirty) && !placeOfBirth.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="placeOfBirth.errors?.required">
              Введите место рождения.
            </span>
          </span>
                    </div>
                    <!--
                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="cityOfResidence">Город Регистрации</label>
                        <select type="text" class="form-control" id="cityOfResidence" name="cityOfResidence" [(ngModel)]="client.cityOfResidence" #cityOfResidence="ngModel" required [ngClass]="{'is-invalid': (cityOfResidence.touched || cityOfResidence.dirty) && !cityOfResidence.valid}">
                            <option *ngFor="let city of cities" [ngValue]="city">{{city.cityName}}</option>
                        </select>
                        <span class="invalid-feedback">
            <span *ngIf="cityOfResidence.errors?.required">
              Выберите город регистрации.
            </span>
          </span>
                    </div>
-->
                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="addressOfResidence">Адрес Проживания</label>
                        <input type="text" class="form-control" id="addressOfResidence" name="addressOfResidence"
                               value="${client.addressOfResidence!''}" [(ngModel)]="client.addressOfResidence" #addressOfResidence="ngModel" required [ngClass]="{'is-invalid': (addressOfResidence.touched || addressOfResidence.dirty) && !addressOfResidence.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="addressOfResidence.errors?.required">
              Введите адрес проживания.
            </span>
          </span>
                    </div>

                    <!--        <div class="col-12 col-lg-6 pr-lg-3">-->
                    <!--          <label for="cityOfRegistry">City Of Registry</label>-->
                    <!--          <select type="text" class="form-control" id="cityOfRegistry" name="cityOfRegistry" [(ngModel)]="client.cityOfRegistry" #cityOfRegistry="ngModel" required [ngClass]="{'is-invalid': (cityOfRegistry.touched || cityOfRegistry.dirty) && !cityOfRegistry.valid}">-->
                    <!--            <option *ngFor="let city of cities" [ngValue]="city">{{city.cityName}}</option>-->
                    <!--          </select>-->
                    <!--          <span class="invalid-feedback">-->
                    <!--            <span *ngIf="cityOfRegistry.errors?.required">-->
                    <!--              Please enter a city of registry.-->
                    <!--            </span>-->
                    <!--          </span>-->
                    <!--        </div>-->

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="addressOfRegistry">Адрес Регистрации</label>
                        <input type="text" class="form-control" id="addressOfRegistry" name="addressOfRegistry"
                               value="${client.addressOfRegistry!''}" [(ngModel)]="client.addressOfRegistry" #addressOfRegistry="ngModel" required [ngClass]="{'is-invalid': (addressOfRegistry.touched || addressOfRegistry.dirty) && !addressOfRegistry.valid}">
                        <span class="invalid-feedback">
            <span *ngIf="addressOfRegistry.errors?.required">
              Введите адрес регистрации.
            </span>
          </span>
                    </div>
<!--
                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="maritalStatus">Семейное Положение</label>
                        <select type="text" class="form-control" id="maritalStatus" name="maritalStatus" [(ngModel)]="client.maritalStatus" required #maritalStatus="ngModel" [ngClass]="{'is-invalid': (maritalStatus.touched || maritalStatus.dirty) && !maritalStatus.valid}">
                            <option *ngFor="let maritalStatus of maritalStatuses" [ngValue]="maritalStatus">{{maritalStatus.status}}</option>
                        </select>
                        <span class="invalid-feedback">
            <span *ngIf="maritalStatus.errors?.required">
              Выберите семейное положение.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="citizenship">Гражданство</label>
                        <select type="text" id="citizenship" class="form-control" name="citizenship" [(ngModel)]="client.citizenship" #citizenship="ngModel" required [ngClass]="{'is-invalid': (citizenship.touched || citizenship.dirty) && !citizenship.valid}">
                            <option *ngFor="let citizenship of citizenships" [ngValue]="citizenship">{{citizenship.countryName}}</option>
                        </select>
                        <span class="invalid-feedback">
            <span *ngIf="citizenship.errors?.required">
              Выберите гражданство.
            </span>
          </span>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="disability">Инвалидность</label>
                        <select type="text" class="form-control" id="disability" name="disability" [(ngModel)]="client.disability" #disability="ngModel" [ngClass]="{'is-invalid': (disability.touched || disability.dirty) && !disability.valid}">
                            <option *ngFor="let disability of disabilities" [ngValue]="disability">{{disability.name}}</option>
                        </select>
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="pensioner">Пенсионер</label>
                        <input type="checkbox" id="pensioner" class="form-control" name="true" [(ngModel)]="client.pensioner">
                    </div>

                    <div class="col-12 col-lg-6 pr-lg-3">
                        <label for="dutyBound">Военнообязанный</label>
                        <input type="checkbox" id="dutyBound" class="form-control" name="true" [(ngModel)]="client.dutyBound">
                    </div>
                    -->
                </div>
            </form>
        </div>
    </div>
    </form>
</@c.page>
