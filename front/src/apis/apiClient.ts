import axios, { AxiosInstance } from "axios";
import { API_BASE_URL } from "./url";
import { AuthApi } from "./interfaces/authApi";
import { IngredientApi, IngredientOrderType } from "./interfaces/ingredientApi";
import { RecipeApi, RecipeOrderType } from "./interfaces/recipeApi";
import { SearchApi } from "./interfaces/searchApi";
import { MyRecipeApi } from "./interfaces/myRecipeApi";
import { MyInfoApi } from "./interfaces/myInfoApi";
import { HighClass } from "./responses/highClass";
import { IngredientDetailInfo } from "./responses/ingredientDetailInfo";
import { IngredientInfo } from "./responses/ingredientInfo";
import { OfflineMartInfo } from "./responses/offlineMartInfo";
import { RecipeDetailInfo } from "./responses/recipeDetailInfo";
import { RecipeInfo } from "./responses/recipeInfo";
import { RecipeOrderInfo } from "./responses/recipeOrderInfo";
import { YoutubeInfo } from "./responses/youtubeInfo";
import { SearchResult } from "./responses/searchResult";
import { MyRecipeDetailInfo } from "./responses/myRecipeDetailInfo";
import { MyRecipeInfo } from "./responses/myRecipeInfo";
import { MyRecipeIngredientInfo } from "./responses/myRecipeIngredientInfo";
import { LimitPriceNoticeInfo } from "./responses/limitPriceNoticeInfo";
import { LoginRes } from "./responses/loginRes";
import * as Dummy from "./dummy/dummyApi";

export class ApiClient
  implements AuthApi, IngredientApi, RecipeApi, SearchApi, MyRecipeApi, MyInfoApi
{
  private static instance: ApiClient;
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = this.createAxiosInstance();
  }
  async register(userName: string, password: string): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async checkId(userName: string): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async login(userName: string, password: string): Promise<LoginRes> {
    throw new Error("Method not implemented.");
  }
  async withdrawal(userName: string): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getIngredientList(
    orderType: IngredientOrderType,
    highClassId: number = 0,
  ): Promise<IngredientInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getIngredientList), 2000));
  }
  async getBookmarkIngredientList(userName: string): Promise<IngredientInfo[]> {
    return new Promise((resolve) =>
      setTimeout(() => resolve(Dummy.getBookmarkIngredientList), 2000)
    );
  }
  async getIngredientDetailInfo(ingredientId: number): Promise<IngredientDetailInfo> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getIngredientDetailInfo), 2000));
  }
  async getIngredientHighClassList(): Promise<HighClass[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getIngredientHighClassList), 2000));
  }
  async putBookmarkIngredient(userName: string, ingredientId: number): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async putBasketIngredient(userName: string, ingredientId: number): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getOfflineMartList(
    ingredientId: number,
    southWestLatitude: number,
    southWestLongitude: number,
    northEastLatitude: number,
    northEastLongitude: number
  ): Promise<OfflineMartInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getOfflineMartList), 2000));
  }
  async getOfflineMartDetailInfo(storeId: number): Promise<IngredientInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getIngredientList), 2000));
  }
  async getBasketIngredientList(userName: string): Promise<IngredientInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getBasketIngredientList), 2000));
  }
  async getRecipeList(
    orderType: RecipeOrderType,
    page: number,
    size: number
  ): Promise<RecipeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getRecipeList), 2000));
  }
  async getRecipeWithBasketList(
    userName: string,
    orderType: RecipeOrderType,
    page: number,
    size: number
  ): Promise<RecipeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getRecipeList), 2000));
  }
  async getBookmarkRecipeList(userName: string): Promise<RecipeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getRecipeList), 2000));
  }
  async getRecipeDetailInfo(recipeId: number): Promise<RecipeDetailInfo> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getRecipeDetailInfo), 2000));
  }
  async putBookmarkRecipe(userName: string, recipeId: number): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getRecipeOrderList(recipeId: number): Promise<RecipeOrderInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getRecipeOrderList), 2000));
  }
  async putBasketAllRecipeIngredient(userName: string, recipeId: number): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getPopularYoutubeList(): Promise<YoutubeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getPopularYoutubeList), 2000));
  }
  async search(keyword: string): Promise<SearchResult> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.search), 2000));
  }
  async postMyRecipe(
    userName: string,
    myRecipeName: string,
    ingredientList: MyRecipeIngredientInfo[]
  ): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async updateMyRecipe(
    userName: string,
    myRecipeId: number,
    myRecipeName: string,
    ingredientList: MyRecipeIngredientInfo[]
  ): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getMyRecipeList(userName: string): Promise<MyRecipeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getMyRecipeList), 2000));
  }
  async getMyRecipeDetailInfo(userName: string, myRecipeId: number): Promise<MyRecipeDetailInfo> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getMyRecipeDetailInfo), 2000));
  }
  async getMyRecipeIngredientList(
    userName: string,
    myRecipeId: number
  ): Promise<MyRecipeIngredientInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getMyRecipeIngredientList), 2000));
  }
  async deleteMyRecipe(userName: string, myRecipeId: number): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async putAllergy(userName: string, ingredientIdList: number[]): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getAllergyIngredientList(userName: string): Promise<number[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getAllergyIngredientList), 2000));
  }
  async postLimitPriceNotice(
    userName: string,
    limitPriceNoticeInfoList: LimitPriceNoticeInfo[]
  ): Promise<void> {
    throw new Error("Method not implemented.");
  }
  async getLimitPriceList(userName: string): Promise<LimitPriceNoticeInfo[]> {
    return new Promise((resolve) => setTimeout(() => resolve(Dummy.getLimitPriceList), 2000));
  }

  static getInstance(): ApiClient {
    return this.instance || (this.instance = new this());
  }

  registerToken(newToken: string) {
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
    // } else if (localStorage.getItem("token")) {
      // headers.Authorization = `Bearer ${localStorage.getItem("token")}`;
    }

    return axios.create({
      baseURL: API_BASE_URL,
      timeout: 1000,
      headers,
    });
  };
}
