import axios, { AxiosInstance } from "axios";
import { API_BASE_URL } from "./url";

export class ApiClient {
  private static instance: ApiClient;
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = this.createAxiosInstance();
  }

  static getInstance(): ApiClient {
    return this.instance || (this.instance = new this());
  }

  login(newToken: string) {
    this.axiosInstance = this.createAxiosInstance(newToken);
  }

  logout() {
    this.axiosInstance = this.createAxiosInstance();
  }

  private createAxiosInstance = (token?: string) => {
    const headers: any = {
      "content-type": "application/json",
    };

    if (token) {
      headers.Authorization = `Bearer ${token}`;
    } else if (localStorage.getItem("token")) {
      headers.Authorization = `Bearer ${localStorage.getItem("token")}`;
    }

    return axios.create({
      baseURL: API_BASE_URL,
      timeout: 1000,
      headers,
    });
  };

  
}
