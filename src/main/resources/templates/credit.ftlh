<#import "parts/common.ftlh" as c>

<@c.page>
    <#if credit??>
        <form method="post">
        <div class="row no-gutters placement">
            <div class="col-12">

                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="row no-gutters">
                        <div class="col-12">
                            <a class="btn btn-primary" href="/creditEd">
                                Edit
                            </a>
                        </div>
                    </div>

                <div class="row no-gutters padding-top">
                    <div class="card w-100">
                        <div class="card-body">
                            <h3>${credit.client.lastName} ${credit.client.firstName} ${credit.client.middleName}</h3>
                            <p>Дата Заключения Кредита: ${credit.date}</p>
                            <p>Сумма Кредита: ${credit.sum}</p>
                            <p>Валюта: ${credit.type.currency}</p>
                            <p>Тип Кредита: ${credit.type.name}</p>
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
        </form>
        <#else>
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="row no-gutters">
                <div class="col-12">
                    <a class="btn btn-primary" href="/creditEd">
                        Добавить кредит
                    </a>
                </div>
            </div>
        </form>
    </#if>



</@c.page>
