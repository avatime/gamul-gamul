import { InputRounded, Translate } from "@mui/icons-material";
import { Box, Input, Stack } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { ChangeEvent, useState } from "react";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Navbar } from "../src/components/Navbar";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { ApiClient } from "../src/apis/apiClient";
import { setCookie } from "../src/utils/cookie";
import { SearchBar } from "../src/components/SearchBar";

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

  const onClickLogin = () => {
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
        5;
        if (code === 401 || code === 404) {
          alert("아이디와 비밀번호를 다시 확인해주세요");
        } else if (code === 500) {
          alert("잠시 후 다시 시도해주세요!");
        }
      });
  };
  return (
    <div>
      <Box>
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
          />
          <Box p={3} />
          <Input
            type="password"
            color="success"
            placeholder="비밀번호"
            sx={{ width: "60vw", maxWidth: "500px" }}
            value={inputPw}
            onChange={handleInputPw}
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
        <Navbar activeIndex={4} />
    
    </div>
  );
};

export default LoginPage;
