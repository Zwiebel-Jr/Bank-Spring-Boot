<#import "parts/common.ftlh" as c>

<@c.page>
<a href="/users/export/excelCred">Export to Excel</a>
<table>
    <thead>
<div class="row no-gutters placement">
    <div class="col-12">
        <#if credit??>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="row no-gutters padding-top" *ngIf="credits.length">
                <table [value]="credits" class="ui-table-responsive" selectionMode="single" (onRowSelect)="rowSelected($event.data.id)">
                    <ng-template pTemplate="header">
                        <tr>
                            <th width="400">Клиент</th>
                             <th width="400"> Тип Кредита</th>
                            <th width="400">Сумма Кредита</th>
                             <th>Валюта</th>
                            <th width="400">Дата Заключения Кредита</th>
                        </tr>
                    </ng-template>
                    <ng-template pTemplate="body" let-credit let-rowData>
                        <#list credits as cred>
                            <tr onclick="window.location='http://localhost:8080/';" [pSelectableRow]="rowData">
                                <td>${cred.client.lastName} ${cred.client.firstName}</td>
                                 <td>${cred.type.name}</td>
                                <td>${cred.sum}</td>
                                 <td>${cred.type.currency}</td>
                                <td>${cred.date}</td>
                            </tr>
                        </#list>
                    </ng-template>
                </table>
            </div>
        <#else>
            <!--
            <div class="row no-gutters">
                <div class="col-12">
                    <button type="button" class="btn btn-primary" [routerLink]="['/credits/add/credit']">Добавить Кредит</button>
                </div>
            </div>-->
            <div class="row no-gutters padding-top" *ngIf="!credits.length">
                <h3>Оформленных Кредитов Пока Нет</h3>
            </div>


        </#if>


    </div>
</div>
</@c.page>

