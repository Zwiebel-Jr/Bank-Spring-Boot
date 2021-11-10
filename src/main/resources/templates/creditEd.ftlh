<#import "parts/common.ftlh" as c>

<@c.page>

<div class="row no-gutters placement">

    <app-form-buttons [validToSave]="generalForm.valid && generalForm.dirty" [validToDelete]="validToDelete" (save)="save()" (delete)="delete()" (back)="back()"></app-form-buttons>

    <div class="col-12">
        <form #generalForm="ngForm" novalidate enctype="multipart/form-data" method="post" >
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group row no-gutters" *ngIf="credit">

                <div class="col-12 col-lg-6 pr-lg-3">
                    <label for="sum">Сумма Кредита</label>
                    <input type="number" class="form-control" id="sum" name="sum" [(ngModel)]="credit.sum" #sum="ngModel" required [ngClass]="{'is-invalid': (sum.touched || sum.dirty) && !sum.valid}">
                    <span class="invalid-feedback">
            <span *ngIf="sum.errors?.required">
              Введите сумму кредита.
            </span>
          </span>
                </div>
                <div class="col-12 col-lg-6 pr-lg-3">
                    <label for="type">Тип Кредита</label>
                    <select type="text" class="form-control" id="type" name="type" (ngModelChange)="creditTypeChange($event)" [(ngModel)]="credit.type.id" #type="ngModel" required [ngClass]="{'is-invalid': (type.touched || type.dirty) && !type.valid}">
                        <#list creditTypes as creditType>
                        <option *ngFor="let creditType of creditTypes" [ngValue]="creditType.id">${creditType.name}</option>
                        </#list>
                    </select>
                    <span class="invalid-feedback">
            <span *ngIf="type.errors?.required">
              Выберите вид кредита.
            </span>
          </span>
                </div>

                <div class="col-12 col-lg-6 pl-lg-3">
                    <label for="date">Дата Заключения Кредита</label>
                    <input type="text" class="form-control" id="date" name="date" [(ngModel)]="credit.date" #date="ngModel" required  pattern="^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}$" [ngClass]="{'is-invalid': (date.touched || date.dirty) && !date.valid}">
                    <span class="invalid-feedback">
            <span *ngIf="date.errors?.required">
              Введите дату.
            </span>
            <span *ngIf="date.errors?.pattern">
              Введите дату в формате(дд/мм/гггг).
            </span>
          </span>
                </div>
        <#if credit.type??>
                <div *ngIf="creditType" class="col-12 col-lg-6 pr-lg-3">
                    <label for="interestRate">Процент по Кредиту</label>
                    <p id="interestRate">${credit.type.interestRate}</p>
                </div>

                <div *ngIf="creditType?.period" class="col-12 col-lg-6 pl-lg-3">
                    <label for="period">Срок Вклада</label>
                    <p id="period">${credit.type.period}</p>
                </div>
        </#if>
            </div>
            <div class="row no-gutters">
                <div class="col-12">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>

        </form>

    </div>
</div>

</@c.page>
