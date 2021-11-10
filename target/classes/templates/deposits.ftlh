<#import "parts/common.ftlh" as c>

<@c.page>

<#if debits??>
    <a href="/users/export/excelDeb">Export to Excel</a>
    <div class="row no-gutters placement">
        <div class="col-12">

            <div class="row no-gutters padding-top" *ngIf="debits.length">
                <table [value]="debits" class="ui-table-responsive" selectionMode="single" (onRowSelect)="rowSelected($event.data.id)">
                    <ng-template pTemplate="header">
                        <tr>
                            <th width="400">Клиент</th>
                            <th width="400">Тип Депозита</th>
                            <th width="400">Сумма Депозита</th>
                            <th>Валюта</th>
                            <th width="400">Дата Заключения Депозита</th>
                        </tr>
                    </ng-template>
                    <ng-template pTemplate="body" let-debit let-rowData>
                        <#list debits as debit>
                            <tr [pSelectableRow]="rowData">
                                <td>${debit.client.lastName} ${debit.client.firstName}</td>
                                <td>${debit.type.name}</td>
                                <td>${debit.sum}</td>
                                <td>${debit.type.currency}</td>
                                <td>${debit.date}</td>
                            </tr>
                        </#list>
                    </ng-template>
                </table>
            </div>
        </div>
    </div>
    <#else>
        <div class="row no-gutters">
            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

            </div>
        </div>
        <div class="row no-gutters padding-top" *ngIf="!debits.length">
            <h3>Оформленных Депозитов Пока Нет</h3>
        </div>
</#if>

</@c.page>
