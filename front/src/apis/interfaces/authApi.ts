import { LoginRes } from "../responses/loginRes";

export interface AuthApi {
  /**
   * 회원가입
   * @param userName 유저 ID
   * @param password 비밀번호
   */
  register(userName: string, password: string): Promise<void>;
  /**
   * ID 중복 검사
   * @param userName 유저 ID
   */
  checkId(userName: string): Promise<void>;
  /**
   * 로그인
   * @param userName 유저 ID
   * @param password 비밀번호
   */
  login(userName: string, password: string): Promise<LoginRes>;
  /**
   * 회원 탈퇴
   * @param userName 유저 ID
   */
  withdrawal(userName: string): Promise<void>;
}
