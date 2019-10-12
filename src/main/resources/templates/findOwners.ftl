<#import "parts/common.ftl" as c >
<@c.page>

    <h2>Find Owners</h2>
    <form method="get" action="/owners" class="form-inline">
            <input type="text" name="owner" class="form-control" placeholder="Enter last name">
            <button type="submit" class="btn btn-success ml-2">Search</button>
        </form>

    </form>

    <br />
    <a class="btn btn-success" href="/owners/new"  role="button">Add Owner</a>
<#--    <a class="btn btn-default"  href="${/owners/new}">Add Owner</a>-->
</@c.page>
