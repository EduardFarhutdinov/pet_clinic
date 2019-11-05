<#import "../parts/common.ftl" as c >
<@c.page>
    <h2>
        Pet
    </h2>

    <form method="post"  action="/owners/${owner.id}/pets/${pet.id}/edit" >
        <div class="form-group" >
            <label for="Owner">Owner</label>
            <div class="col-sm-10">
                <span>${pet.owner.firstName + ' ' + pet.owner.lastName}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="Name">Name</label>
            <#if error??>
                ${error}
            </#if>
            <input type="text" style="<#if error??>border-color: red</#if>" class="form-control" name="name" id="name" placeholder="name" value="${pet.name?ifExists}">

        </div>
        <div class="form-group">
            <label for="Birth Date">Birth Date</label>
            <input type="date" pattern="yyyy-MM-dd" class="form-control" name="birthDate"  id="birthDate" placeholder="Birth Date" value="${pet.birthDate?ifExists}">
        </div>
        <select class="custom-select custom-select-lg mb-3" name="type">
            <#--            <#list types as type>-->
            <#--                <option name="type">${type}</option>-->
            <#--            </#list>-->
            <#if (pet.new == false)>
                <option selected>${pet.type}</option>
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
