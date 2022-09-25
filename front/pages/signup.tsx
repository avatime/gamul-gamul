import { Box, FormHelperText, Input, Paper, Stack, Typography } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { ChangeEvent, useEffect, useState } from "react";
import { ApiClient } from "../src/apis/apiClient";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { BackHeader } from "../src/components/BackHeader";

interface IProps {}

const SignupPage: NextPage<IProps> = (props) => {
  const apiClient = ApiClient.getInstance();
  const router = useRouter();

  const [inputId, setInputId] = useState("");
  const [inputPw, setInputPw] = useState("");

  const [nextId, setNextId] = useState(false);
  const [nextPw, setNextPw] = useState(false);
  const [isCheckId, setIsCheckId] = useState(false);

  const [idHelperText, setIdHelperText] = useState("아이디는 3자 이상 10자 이하여야 합니다.");

  // 비밀번호 특수문자 검사를 위한 정규식표현.
  const specialLetter = inputPw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
  // 특수문자 1자 이상, 전체 8자 이상일것.
  const isValidPassword = inputPw.length >= 8 && inputPw.length <= 16 && specialLetter >= 1;
  // 3자 이상 10자 이하
  const [isValidId, setIsValidId] = useState(false);

  useEffect(() => {
    const temp = inputId.length >= 3 && inputId.length <= 10;
    setIsValidId(temp);

    if (temp) {
      setIdHelperText("");
      apiClient
        .checkId(inputId)
        .then((res) => {
          console.log(res);
          setIsCheckId(true);
        })
        .catch((e) => {
          const code = e.response.status;
          console.log(e);

          console.log(code);

          if (code === 404) {
            setIdHelperText("중복된 아이디입니다.");
          } else if (code === 500) {
            alert("잠시 후에 다시 시도해주세요");
          }
        });
    } else {
      setIdHelperText("아이디는 3자 이상 10자 이하여야 합니다.");
    }
  }, [apiClient, inputId]);

  const handleId = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const id = e.target.value;
    setInputId(id);
  };

  const handlePw = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setInputPw(e.target.value);
  };
  const changeIdcomp = () => {
    setNextId(true);
  };

  const changePwcomp = () => {
    apiClient
      .register(inputId, inputPw)
      .then((res) => setNextPw(true))
      .catch((e) => {
        const code = e.response.status;
        if (code === 500) {
          alert("잠시 후 다시 시도해주세요.");
        }
      });
  };

  return (
    <div>
      <Mobile>
        <Box>
          {!nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>아이디를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidId && isCheckId ? "success" : "error"}
                  placeholder="아이디"
                  sx={{ maxWidth: "500px" }}
                  value={inputId}
                  onChange={handleId}
                />
                <FormHelperText id="component-helper-text">{idHelperText}</FormHelperText>

                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text="확인"
                    onClick={changeIdcomp}
                    height="50px"
                    width="60vw"
                    maxWidth="500px"
                    fontSize={""}
                    disabled={!isValidId}
                  />
                </Box>
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>비밀번호를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidPassword ? "success" : "error"}
                  type="password"
                  placeholder="비밀번호"
                  sx={{ width: "60vw", maxWidth: "500px" }}
                  value={inputPw}
                  onChange={handlePw}
                />
                <Box sx={{ width: "60vw" }}>
                  <FormHelperText id="component-helper-text">
                    {isValidPassword
                      ? ""
                      : "비밀번호는 특수문자 1자이상 포함 8자 이상 16자 이하여야 합니다."}
                  </FormHelperText>
                </Box>
                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text={"확인"}
                    onClick={changePwcomp}
                    height={"50px"}
                    width={"60vw"}
                    maxWidth={"500px"}
                    fontSize={""}
                    disabled={!isValidPassword}
                  />
                </Box>
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && nextPw ? (
            <Stack
              direction="column"
              sx={{
                position: "absolute",
                left: 0,
                right: 0,
                top: "30%",
                textAlign: "center",
                justifyContent: "center",
                alignItems: "center",
                height: "200px",
                maxWidth: "500px",
                margin: "auto",
              }}
            >
              <Box sx={{ textAlign: "center" }}>
                <Box p={10} />
                <Box sx={{ height: "50px", alignItems: "center", justifyContent: "center" }}>
                  <Typography
                    sx={{ fontWeight: "bold", width: "60vw", fontSize: "large", ZIndex: 10 }}
                  >
                    축하합니다
                  </Typography>
                </Box>
                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text={"가물가물 시작하기"}
                    onClick={() => router.push("/login")}
                    height={"50px"}
                    width={"60vw"}
                    maxWidth={"500px"}
                    fontSize={""}
                    disabled={false}
                  />
                </Box>
              </Box>
            </Stack>
          ) : (
            ""
          )}
        </Box>
      </Mobile>
      <Tablet>
        <Box>
          {!nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>아이디를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidId && isCheckId ? "success" : "error"}
                  placeholder="아이디"
                  sx={{ maxWidth: "500px" }}
                  value={inputId}
                  onChange={handleId}
                />
                <FormHelperText id="component-helper-text">{idHelperText}</FormHelperText>

                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text="확인"
                    onClick={changeIdcomp}
                    height="50px"
                    width="60vw"
                    maxWidth="500px"
                    fontSize={""}
                    disabled={!isValidId}
                  />
                </Box>
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>비밀번호를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidPassword ? "success" : "error"}
                  type="password"
                  placeholder="비밀번호"
                  sx={{ width: "60vw", maxWidth: "500px" }}
                  value={inputPw}
                  onChange={handlePw}
                />
                <Box sx={{ width: "60vw" }}>
                  <FormHelperText id="component-helper-text">
                    {isValidPassword
                      ? ""
                      : "비밀번호는 특수문자 1자이상 포함 8자 이상 16자 이하여야 합니다."}
                  </FormHelperText>
                </Box>
                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text={"확인"}
                    onClick={changePwcomp}
                    height={"50px"}
                    width={"60vw"}
                    maxWidth={"500px"}
                    fontSize={""}
                    disabled={!isValidPassword}
                  />
                </Box>
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && nextPw ? (
            <Stack
              direction="column"
              sx={{
                position: "absolute",
                left: 0,
                right: 0,
                top: "30%",
                textAlign: "center",
                justifyContent: "center",
                alignItems: "center",
                height: "200px",
                maxWidth: "500px",
                margin: "auto",
              }}
            >
              <Box sx={{ textAlign: "center" }}>
                <Box p={10} />
                <Box sx={{ height: "50px", alignItems: "center", justifyContent: "center" }}>
                  <Typography
                    sx={{ fontWeight: "bold", width: "60vw", fontSize: "large", ZIndex: 10 }}
                  >
                    축하합니다
                  </Typography>
                </Box>
                <Box p={6} />
                <Box sx={{ position: "fixed", bottom: "30px" }}>
                  <ButtonFill
                    text={"가물가물 시작하기"}
                    onClick={() => router.push("/login")}
                    height={"50px"}
                    width={"60vw"}
                    maxWidth={"500px"}
                    fontSize={""}
                    disabled={false}
                  />
                </Box>
              </Box>
            </Stack>
          ) : (
            ""
          )}
        </Box>
      </Tablet>
      <Desktop>
        <Box>
          {!nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>아이디를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidId && isCheckId ? "success" : "error"}
                  placeholder="아이디"
                  sx={{ maxWidth: "500px" }}
                  value={inputId}
                  onChange={handleId}
                />
                <FormHelperText id="component-helper-text">{idHelperText}</FormHelperText>

                <Box p={6} />
                <ButtonFill
                  text="확인"
                  onClick={changeIdcomp}
                  height="50px"
                  width="60vw"
                  maxWidth="500px"
                  fontSize={""}
                  disabled={!isValidId}
                />
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && !nextPw ? (
            <>
              <BackHeader text={""} textColor={""} />
              <Stack
                direction="column"
                sx={{
                  position: "absolute",
                  width: "60vw",
                  left: 0,
                  right: 0,
                  top: "30%",
                  maxWidth: "500px",
                  margin: "auto",
                }}
              >
                <Typography sx={{ fontWeight: "bold" }}>비밀번호를 입력해주세요.</Typography>
                <Box p={2} />
                <Input
                  color={isValidPassword ? "success" : "error"}
                  type="password"
                  placeholder="비밀번호"
                  sx={{ width: "60vw", maxWidth: "500px" }}
                  value={inputPw}
                  onChange={handlePw}
                />
                <Box sx={{ width: "60vw" }}>
                  <FormHelperText id="component-helper-text">
                    {isValidPassword
                      ? ""
                      : "비밀번호는 특수문자 1자이상 포함 8자 이상 16자 이하여야 합니다."}
                  </FormHelperText>
                </Box>
                <Box p={6} />

                <ButtonFill
                  text={"확인"}
                  onClick={changePwcomp}
                  height={"50px"}
                  width={"60vw"}
                  maxWidth={"500px"}
                  fontSize={""}
                  disabled={!isValidPassword}
                />
              </Stack>
            </>
          ) : (
            ""
          )}
          {nextId && nextPw ? (
            <Stack
              direction="column"
              sx={{
                position: "absolute",
                left: 0,
                right: 0,
                top: "30%",
                textAlign: "center",
                justifyContent: "center",
                alignItems: "center",
                height: "200px",
                maxWidth: "500px",
                margin: "auto",
              }}
            >
              <Box sx={{ textAlign: "center" }}>
                <Box p={10} />
                <Box sx={{ height: "50px", alignItems: "center", justifyContent: "center" }}>
                  <Typography
                    sx={{ fontWeight: "bold", width: "60vw", fontSize: "large", ZIndex: 10 }}
                  >
                    축하합니다
                  </Typography>
                </Box>
                <Box p={6} />

                <ButtonFill
                  text={"가물가물 시작하기"}
                  onClick={() => router.push("/login")}
                  height={"50px"}
                  width={"60vw"}
                  maxWidth={"500px"}
                  fontSize={""}
                  disabled={false}
                />
              </Box>
            </Stack>
          ) : (
            ""
          )}
        </Box>
      </Desktop>
    </div>
  );
};

export default SignupPage;
