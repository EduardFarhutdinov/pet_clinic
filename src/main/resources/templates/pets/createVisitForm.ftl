<#import "../parts/common.ftl" as c >
<@c.page>
    <h2>Visit</h2>
    <b>Pet</b>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Birth Date</th>
            <th>Type</th>
            <th>Owner</th>
        </tr>
        </thead>
        <tr>
            <td>${pet.name}</td>
            <td>${pet.birthDate}</td>
            <td >${pet.type}</td>
            <td>${pet.owner.firstName + ' ' + pet.owner.lastName}</td>
        </tr>
    </table>

    <form class="form-horizontal" method="post" action="/owners/${pet.owner.id}/pets/${pet.id}/visits/new">
        <div class="form-group has-feedback">
            <input type="date"  class="form-control" id="date" name="date" placeholder="Visit Date">
        </div>
        <div>
            <input type="text"  class="form-control" id="desc" name="desc"  placeholder="Description" >
        </div>

<#--            <div class="col-sm-offset-2 col-sm-10">-->
<#--                <button type="submit" class="btn btn-primary">Add Visit</button>-->
<#--            </div>-->
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="petId" value="${pet.id}" />
                <button class="btn btn-default" type="submit">Add Visit</button>
            </div>
        </div>
    </form>
</@c.page>
