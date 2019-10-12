<#import "parts/common.ftl" as c>
<@c.page>


    <h2>Owners</h2>

    <table class="table">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Address</th>
            <th>City</th>
            <th style="width: 120px">Telephone</th>
            <th>Pets</th>
        </tr>
        </thead>
        <tbody>
            <#list owners as owner>
                <tr>
                    <td><a href="/owners/${owner.id}"/> ${owner.firstName + " " + owner.lastName}</td>
                    <td>${owner.address}</td>
                    <td>${owner.city}</td>
                    <td>${owner.telephone}</td>
                    <td><#list owner.pets as pet>${pet.name}</#list></td>
                </tr>
            </#list>
        </tbody>
    </table>
</@c.page>
