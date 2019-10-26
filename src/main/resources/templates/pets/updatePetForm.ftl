<#import "../parts/common.ftl" as c >
<@c.page>
    <h2>
        Pet
    </h2>

    <form method="post"  action="/owners/${ownerEditPEt.id}/pets/${editPet.id}/edit" >
        <div class="form-group" >
            <label for="Owner">Owner</label>
            <div class="col-sm-10">
                <span>${ownerEditPEt.firstName + ' ' + ownerEditPEt.lastName}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="Name">Name</label>
            <input type="text" style="<#if error??>border-color: red</#if>" class="form-control" name="name" id="name" placeholder="name" value="${editPet.name?ifExists}">

        </div>
        <div class="form-group">
            <label for="Birth Date">Birth Date</label>
            <input type="date" pattern="yyyy-MM-dd" class="form-control" name="birthDate"  id="birthDate" placeholder="Birth Date" value="${editPet.birthDate?ifExists}">
        </div>
        <select class="custom-select custom-select-lg mb-3" name="type">
            <#--            <#list types as type>-->
            <#--                <option name="type">${type}</option>-->
            <#--            </#list>-->
            <#if (editPet.new == false)>
                <option selected>${editPet.type}</option>
                <#list types as type>
                    <option >${type}</option>
                </#list>
            <#else >
                <#list types as type>
                    <option value="" selected disabled hidden>Choose here</option>
                    <option >${type}</option>
                </#list>

            </#if>

        </select>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</@c.page>
