<#import "../parts/common.ftl" as c >
<@c.page>
    <form action="/owners/${owner.id}/edit" method="post" >
        <h2>Owner</h2>
        <div class="form-row">
            <div class="form-group col-md-3">
                <input type="text" name="firstName" class=" form-control" placeholder="First Name" value="${owner.firstName?ifExists}">
            </div>

            <div class="form-group col-md-3">
                <input type="text" name="lastName" class="form-control" placeholder="Last Name" value="${owner.lastName?ifExists}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" name="address" class="form-control" placeholder="Address" value="${owner.address?ifExists}" >
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" name="city" class="form-control" placeholder="City" value="${owner.city?ifExists}">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" name="telephone" class="form-control" placeholder="Telephone" value="${owner.telephone?ifExists}">
            </div>
        </div>




        <div class="form-group">
            <button type="submit" class="btn btn-success">Update</button>
        </div>
        </div>
    </form>
</@c.page>
