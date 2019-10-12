<#import "parts/common.ftl" as c >
<@c.page>

    <table id="vets" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Specialties</th>
        </tr>
        </thead>
        <tbody>
        <#list vets.vets as vet>
            <tr>
                <td> ${vet.firstName + " " + vet.lastName}</td>
                <td>
                   <#list vet.specialties as specilty>${specilty.name}</#list>
                    <span><#if vet.nrOfSpecialties == 0>none</#if> </span>

<#--                    ${spesialityText}-->
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
