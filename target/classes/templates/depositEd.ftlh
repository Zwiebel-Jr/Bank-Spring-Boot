<#import "parts/common.ftlh" as c>

<@c.page>

<div class="row no-gutters placement">

    <app-form-buttons [validToSave]="generalForm.valid && generalForm.dirty" [validToDelete]="validToDelete" (save)="save()" (delete)="delete()" (back)="back()"></app-form-buttons>

    <div class="col-12">
        <form #generalForm="ngForm" novalidate enctype="multipart/form-data" method="post">
            <div class="form-group row no-gutters" *ngIf="debit">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="col-12 col-lg-6 pr-lg-3">
                    <label for="sum">Сумма Депозита</label>
                    <input type="number" class="form-control" id="sum" name="sum" [(ngModel)]="debit.sum" #sum="ngModel" required [ngClass]="{'is-invalid': (sum.touched || sum.dirty) && !sum.valid}">
                    <span class="invalid-feedback">
            <span *ngIf="sum.errors?.required">
              Введите сумму депозита.
            </span>
          </span>
                </div>

                <div class="col-12 col-lg-6 pr-lg-3">
                    <label for="type">Тип Депозита</label>
                    <select type="text" class="form-control" id="type" name="type" (ngModelChange)="depositTypeChange($event)" [(ngModel)]="debit.type.id" #type="ngModel" required [ngClass]="{'is-invalid': (type.touched || type.dirty) && !type.valid}">
                        <#list debitTypes as debitType>
                            <option *ngFor="let debitType of debitTypes" [ngValue]="debitType.id">${debitType.name}</option>
                        </#list>
                    </select>
                    <span class="invalid-feedback">
            <span *ngIf="type.errors?.required">
              Выберите вид депозита.
            </span>
          </span>
                </div>

                <div class="col-12 col-lg-6 pl-lg-3">
                    <label for="date">Дата Заключения Депозита</label>
                    <input type="text" class="form-control" id="date" name="date" [(ngModel)]="debit.date" #date="ngModel" required  pattern="^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}$" [ngClass]="{'is-invalid': (date.touched || date.dirty) && !date.valid}">
                    <span class="invalid-feedback">
            <span *ngIf="date.errors?.required">
              Введите дату.
            </span>
            <span *ngIf="date.errors?.pattern">
              Введите дату в формате(дд/мм/гггг).
            </span>
          </span>
                </div>
                <#if debit.type??>
                <div *ngIf="debitType" class="col-12 col-lg-6 pr-lg-3">
                    <label for="interestRate">Процент по Вкладу</label>
                    <p id="interestRate">${debit.type.interestRate!''}</p>
                </div>

                <div *ngIf="debitType?.period" class="col-12 col-lg-6 pl-lg-3">
                    <label for="period">Срок Вклада</label>
                    <p id="period">${debit.type.period}</p>
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
