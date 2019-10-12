<#import "parts/common.ftl" as c >
<@c.page>
    <div><h2>Welcome to our vet clinic!</h2></div>

    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item">
                <img class="d-block w-100" src="/images/prewiew.jpg" width="300" height="600"  alt="Third slide">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Осмотр своих питомцев</h5>
                    <p>Мы производим осмотр ваших любимых питомцев совершенно бесплатно</p>
                </div>
            </div>
            <div class="carousel-item active">
                <img class="d-block w-100" src="/images/vaccine.jpeg"  width="300" height="600"  alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/images/corm.jpg" width="300" height="300" alt="Second slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</@c.page>
