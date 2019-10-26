<#import "../parts/common.ftl" as c >
<@c.page>
    <h2>
        Pet
    </h2>

    <form method="post" action="/owners/${owner.id}/pets/new" >
        <div class="form-group" >
            <label for="Owner">Owner</label>
            <div class="col-sm-10">
                <span>${owner.firstName + ' ' + owner.lastName}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="Name">Name</label>
            <input type="text" style="<#if error??>border-color: red</#if>" class="form-control" name="name" id="name" placeholder="name" >

        </div>
        <div class="form-group">
            <label for="Birth Date">Birth Date</label>
            <input type="date" pattern="yyyy-MM-dd" class="form-control" name="birthDate"  id="birthDate" placeholder="Birth Date" >
        </div>
        <select class="custom-select custom-select-lg mb-3" name="type">
<#--            <#list types as type>-->
<#--                <option name="type">${type}</option>-->
<#--            </#list>-->
            <#if (newPet.new == false)>
                <option selected>${newPet.type}</option>
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

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>
