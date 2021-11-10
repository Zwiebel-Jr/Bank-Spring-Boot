<#import "parts/common.ftlh" as c>

<@c.page>
    <#if debit??>
<div class="row no-gutters placement">
    <div class="col-12">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="row no-gutters">
            <div class="col-12">
                <a class="btn btn-primary" href="/debitEd">
                    Edit
                </a>
            </div>
        </div>
        <div class="row no-gutters padding-top">
            <div class="card w-100">
                <div class="card-body">
                    <h3>${debit.client.lastName} ${debit.client.firstName} ${debit.client.middleName}</h3>
                    <p>Дата Заключения Депозита: ${debit.date}</p>
                    <p>Сумма Депозита: ${debit.sum}</p>
                    <p>Валюта: ${debit.type.currency}</p>
                    <p>Вид Депозита: ${debit.type.name}</p>
                </div>
            </div>
            <div class="w-100" *ngIf="calculatedValues">
                <highcharts-chart
                        [Highcharts] = "highcharts"
                        [options] = "chartOptions"
                        style = "width: 100%; height: 400px; display: block;">
                </highcharts-chart>
            </div>
        </div>
    </div>
</div>
        <#else>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="row no-gutters">
                <div class="col-12">
                    <a class="btn btn-primary" href="/debitEd">
                        Добавить депозит
                    </a>
                </div>
            </div>
    </#if>
</@c.page>
