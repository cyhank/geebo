<!DOCTYPE html>
<!-- thymeleaf 提示功能 -->
<html>
<body>
    <h3>
        ${msg}
    </h3>

    <div class="container">
        <div>
            <img alt="验证码" onclick="this.src='/img/img?d='+new Date()*1" src="/img/img"/>
        </div>
        <form action="verify" method="post">
            <input type="text" name="vrifyCode"/>
            <input type="submit" value="提交"></input>
        </form>
    </div>
</body>
</html>