<jsp:directive.include file="header.jsp"/>
<div class="title w-100 tac">Authorization</div>

<style>
    .btn {
        width: 180px;
        height: 60px;
        cursor: pointer;
        background: transparent;
        border: 1px solid #91C9FF;
        outline: none;
        transition: 1s ease-in-out;
    }

    svg {
        position: absolute;
        left: 0;
        top: 0;
        fill: none;
        stroke: #fff;
        stroke-dasharray: 150, 480;
        stroke-dashoffset: 150;
        transition: 1s ease-in-out;
    }

    .center {
        width: 180px;
        height: 60px;
        position: absolute;
    }

    .btn:hover {
        transition: 1s ease-in-out;
        background: #4F95DA;
    }

    .btn:hover svg {
        stroke-dashoffset: -480;
    }

    .btn span {
        color: white;
        font-size: 18px;
        font-weight: 100;
    }
</style>

<div class="row">
    <div class="col-12 tac">
        <form method="post" action="/fs/authorization">
            <label class="white" for="login">Login</label><br>
            <input type="text" name="login" id="login" required="required" class="form" placeholder="Login" />
            <br>
            <br>
            <label class="white" for="password">Password</label><br>
            <input type="password" id="password" name="password" placeholder="Enter password">
            <br>
            <br>
            <div class="center tac">
                <button class="btn">
                    <svg width="180px" height="60px" viewBox="0 0 180 60" class="border">
                        <polyline points="179,1 179,59 1,59 1,1 179,1" class="bg-line" />
                        <polyline points="179,1 179,59 1,59 1,1 179,1" class="hl-line" />
                    </svg>
                    <span>HOVER ME</span>
                </button>
            </div>
            <br>
            <p class="white">Don't have an account? <a href="/fs/registration">Register</a> for free!</p>
        </form>
    </div>
</div>
<jsp:directive.include file="footer.jsp"/>
