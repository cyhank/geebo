<#--<span style="display: inline">
    <input type="text" name="imageCode" placeholder="验证码" style="width: 50%;"/>
    <img src="/code/image"/>



</span>-->

<!DOCTYPE html>
<!-- thymeleaf 提示功能 -->
<html>
<body>

<div class="container">
    <div class="container">
        <div>
            <img alt="验证码" onclick="this.src='/img/feb?d='+new Date()*1" src="/img/feb"/>
        </div>
        <form action="verify" method="post">
            <input type="text" name="code"/>
            <input type="submit" value="提交"></input>
        </form>
    </div>
</div>
</body>
</html>