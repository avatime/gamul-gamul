import { LoginRes } from './response/loginRes';
export interface AuthApi {
  register(userName: string, password: string): Promise<void>;
  checkId(userName: string): Promise<void>;
  login(userName: string, password: string): Promise<LoginRes>;
  withdrawal(userName: string): Promise<void>;
}
