<#import "../parts/common.ftl" as c >
<@c.page>


    <h2>Owner Information</h2>
    <table class="table table-striped" style="width: 700px;">
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
    <a href="${owner.id}/edit" class="btn btn-success">Edit Owner</a>
    <a href="${owner.id}/pets/new" class="btn btn-success">Add New Pet</a>

    <br />
    <br />
    <br />
    <h2>Pets and Visits</h2>



    <table class="table table-striped-sm" style="font-size:13px">
        <thead>
        <tr>
<#--            <th>id</th>-->
            <th scope="col">Name</th>
            <th scope="col" style="width:150px;">Birth Date</th>
            <th scope="col">Type</th>
            <th></th> //editPet
            <th></th>
            <th></th>
            <th scope="col">Visit Date</th>
            <th scope="col">Description</th>
            <th></th> //Add Visit
        </tr>
        </thead>
        <tbody>
        <#list owner.pets as pet>
            <tr>
                <td hidden>${pet.id}</td>
                <td style="font-size: 13px"> ${pet.name}</td>
                <td style="width:150px;">${pet.birthDate}</td>
                <td>${pet.type}</td>
                <td><a href="${owner.id}/pets/${pet.id}/edit">Edit
                        Pet</a></td>
                <td></td>
                <td></td>

                <#list pet.visits as visit>
                    <td>${visit.date}</td>
                    <td>${visit.description}</td>
                </#list>
                <td></td>
                <td></td>
<#--                <td><a href="${owner.id}/pets/${pet.id}/visits/new">Add Visit</a> </td>-->

                <td><a href="${owner.id}/pets/${pet.id}/visits/new">Add Visit</a> </td>
            </tr>
        </#list>
        </tbody>
        <#--                        <#list owner.pets as pet>-->
        <#--                            <tr>-->
        <#--                                <th scope="col">Name</th>-->
        <#--                                <td style="font-size: 13px"> ${pet.name}</td>-->
        <#--                            </tr>-->
        <#--                            <tr>-->
        <#--                                <th scope="col">Birth Date</th>-->
        <#--                                <td>${pet.birthDate}</td>-->
        <#--                            </tr>-->
        <#--                            <tr>-->
        <#--                                <th scope="col">Type</th>-->
        <#--                                <td>${pet.type}</td>-->
        <#--                            </tr>-->
    </table>


<#--            <table class="table table-borderless" style="width: 400px;font-size:13px;border-spacing:10">-->
<#--                                    <thead>-->
<#--                                    <tr>-->
<#--                                        <th>Visit Date</th>-->
<#--                                        <th>Description</th>-->
<#--                                    </tr>-->
<#--                                    </thead>-->
<#--                                    <tr>-->
<#--                                        <#list pet.visits as visit >-->
<#--                                            <td>${visit.date}</td>-->
<#--                                            <td>${visit.description}</td>-->
<#--                                        </#list>-->
<#--                                    </tr>-->
<#--                                    <tr>-->
<#--                                        <td><a href="${owner.id}/pets/${pet.id}/edit">Edit-->
<#--                                                Pet</a></td>-->
<#--                                        <td><a href="${owner.id}/pets/${pet.id}/visits/new">Add-->
<#--                                                Visit</a></td>-->
<#--                                    </tr>-->
<#--&lt;#&ndash;                        </#list>&ndash;&gt;-->

<#--             </table>-->


</@c.page>
