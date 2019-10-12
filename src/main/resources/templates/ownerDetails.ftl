<#import "parts/common.ftl" as c >
<@c.page>


    <h2>Owner Information</h2>
    <table class="table table-striped">
<#--        <thead>-->
        <tr>
            <th scope="col">Name</th>
            <td> ${owner.firstName + " " + owner.lastName}</td>
        </tr>
<#--        </thead>-->
<#--        <tbody>-->
        <tr>
            <th scope="col">Address</th>
            <td>${owner.address}</td>
        </tr>
        <tr>
            <th scope="col">City</th>
            <td>${owner.city}</td>
        </tr>
        <tr>
            <th scope="col">Telephone</th>
            <td>${owner.telephone}</td>
        </tr>
<#--        </tbody>-->
    </table>
    <a href="/owners/${owner.id}/edit" class="btn btn-success">Edit Owner</a>
<#--    <a th:href="@{{id}/pets/new(id=${owner.id})}" class="btn btn-default">Add-->
<#--        New Pet</a>-->
</@c.page>
