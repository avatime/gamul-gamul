import { Box, Input } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { ChangeEvent, useState } from "react";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { ApiClient } from "../src/apis/apiClient";
import { setCookie } from "../src/utils/cookie";
import { Page } from "../src/components/Page";
import { Mobile } from "../src/components/Mobile";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { KeyboardEvent } from "react";
import bstyles from "../styles/Button.module.css"
interface IProps {}

const LoginPage: NextPage<IProps> = (props) => {
  const router = useRouter();

  const [inputId, setInputId] = useState("");
  const [inputPw, setInputPw] = useState("");
  const [token, setToken] = useState("");

  const handleInputId = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setInputId(e.target.value);
  };
  const handleInputPw = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setInputPw(e.target.value);
  };

  const apiClient = ApiClient.getInstance();

  const login = () => {
    apiClient
      .login(inputId, inputPw)
      .then((res) => {
        setCookie("token", res.access_token);
        setCookie("userName", inputId);
        router.push("/");
        console.log(res);
      })
      .catch((e) => {
        const code = e.response.status;
        console.log(e);

        console.log(code);
        if (code === 401 || code === 404) {
          alert("아이디와 비밀번호를 다시 확인해주세요");
        } else if (code === 500) {
          alert("잠시 후 다시 시도해주세요!");
        }
      });
  };

  const onClickLogin = () => {
    login();
  };

  const enterKey = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.code === 'Enter') {
      console.log(e.code);
      
      login();
    }
  };
  return (
    <Page>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <Box
            sx={{
              position: "absolute",
              left: 0,
              right: 0,
              top: "20%",
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
            }}
          >
            <Input
              color="success"
              placeholder="아이디"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputId}
              onChange={handleInputId}
              onKeyPress={enterKey}/>
              
            <Box p={3} />
            <Input
              type="password"
              color="success"
              placeholder="비밀번호"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputPw}
              onChange={handleInputPw}
              onKeyPress={enterKey}
            />
            <Box p={4} />
            <ButtonFill
              text={"로그인"}
              onClick={onClickLogin}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
            <Box p={1} />
            <ButtonFill
              text={"가물가물 회원 되기"}
              onClick={() => router.push("/signup")}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
          </Box>
        </Box>
      </Mobile>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Box
            sx={{
              position: "absolute",
              left: 200,
              right: 0,
              top: "20%",
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
            }}
          >
            <Input
              color="success"
              placeholder="아이디"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputId}
              onChange={handleInputId}
              onKeyPress={enterKey}
            />
            <Box p={3} />
            <Input
              type="password"
              color="success"
              placeholder="비밀번호"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputPw}
              onChange={handleInputPw}
              onKeyPress={enterKey}
            
            />
            <Box p={4} />
            <input
              type="button"
              value="로그인"
              className={bstyles.buttonFillStyle}
              onClick={onClickLogin}
              disabled={false}
              style={{ height: "50px", width: "60vw", maxWidth: "500px", cursor: "pointer" }}
            />

            <Box p={1} />
            <ButtonFill
              text={"가물가물 회원 되기"}
              onClick={() => router.push("/signup")}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
          </Box>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Box
            sx={{
              position: "absolute",
              left: 150,
              right: 0,
              top: "20%",
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
            }}
          >
            <Input
              color="success"
              placeholder="아이디"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputId}
              onChange={handleInputId}
              onKeyPress={enterKey}
            />
            <Box p={3} />
            <Input
              type="password"
              color="success"
              placeholder="비밀번호"
              sx={{ width: "60vw", maxWidth: "500px" }}
              value={inputPw}
              onChange={handleInputPw}
              onKeyPress={enterKey}
            />
            <Box p={4} />
            <ButtonFill
              text={"로그인"}
              onClick={onClickLogin}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
            <Box p={1} />
            <ButtonFill
              text={"가물가물 회원 되기"}
              onClick={() => router.push("/signup")}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
          </Box>
        </Box>
      </Tablet>
    </Page>
  );
};

export default LoginPage;
