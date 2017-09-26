<style type="text/css">
    #navbar {
        background-color: rgba(0, 153, 255, 0.75);
        min-height: 50px;
        border: 1px solid transparent;
    }
    #navbar .author-name {
        color: #ffffff;
    }
    #navbar .navbar-nav a {
        color: #ffffff;
    }

    #navbar .nav>li>a:focus, #navbar .nav>li>a:hover {
        background: #ffffff;
        color: #0099FF !important;
        font-size: 105%;
    }

    #navbar .author-name {
        min-width: 100px;
        line-height: 50px;
        text-align: center;
    }

    #navbar .query-input-warp {
        line-height: 50px;
        margin-right: 20px;
    }

    #query-input {
        height: 34px;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        background: transparent;
        color: #eeeeee;
        border: 1px solid #eeeeee;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
    #query-input::-moz-placeholder {
        color: #dddddd;
        opacity: 1
    }
    #query-input::-ms-input-placeholder {
        color: #dddddd;
    }
    #query-input::-webkit-input-placeholder {
        color: #dddddd;
    }



    /*toggle */
    .toggle-button {
        display: block;
        width: 43px;
        height: 35px;
        margin-top: 7px;
        border: 1px solid #f5f5f5;
        border-radius: 5px;
        cursor: pointer;
    }

    .toggle-button > div.divider {
        height: 7px;
        width: 80%;
        margin: 0 auto;
        border-bottom: 2px solid #f5f5f5;
        border-radius: 2px;
    }
    .toggle-button:hover {
        background: #0088ee;
    }
    .toggle-list {
        position: absolute;
        display: none;
        top: 50px;
        left: 0;
        right: 0;
        z-index: 9999;
        margin-top: 0;
        border-top: 1px solid #f5f5f5;
        padding-left: 0;
        text-indent: 20px;
        background-color:rgba(255,255,255,0.75);
    }

    .toggle-list > li {
        border-bottom: 1px solid #e7e7e7;
        height: 30px;
        line-height: 30px;
    }
    .toggle-list > li:hover {
        font-size: 120%;
    }
    .toggle-list > li > a {
        display: block;
        color: #0099FF;
        width: 100%;
        height: 100%;
    }
</style>

<div id="navbar">
    <div id="navbar-container">

        <a class="f22 pull-left author-name" href="/">
            <@spring.message "app.name"/>
        </a>

        <!-- /section:basics/navbar.dropdown -->
    <#if categoryList??>
        <nav class="pull-left hidden-sm" role="navigation">
            <ul class="nav navbar-nav">
                <#list categoryList as c>
                    <li>
                        <a href="/${c.code}">${c.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>


        <div class="pull-right hidden show-sm mr-20 toggle-panel">
            <div href="#" class="toggle-button" id="navbar-toggle-btn">
                <div class="divider"></div>
                <div class="divider"></div>
                <div class="divider"></div>

                <ul class="toggle-list">
                    <#list categoryList as c>
                        <li>
                            <a href="/category/${c.code}">${c.name}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </#if>

        <div class="query-input-warp pull-right">
            <form action="/query" method="get" novalidate>
                <input id="query-input" type="text" name="key" value="${key!''}" placeholder="输入查询"/>
            </form>
        </div>
    </div><!-- /.navbar-container -->
</div><!-- /.navbar -->

<script>
    $(function () {
        var $navbarBtn = $("#navbar-toggle-btn");
        $navbarBtn.on('click', function(){
            var toggleList = $navbarBtn.find(".toggle-list");
            toggleList.toggle("hidden");
        });
    });
</script>
