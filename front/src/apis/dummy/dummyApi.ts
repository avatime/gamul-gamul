import { HighClass } from "../responses/highClass";
import { IngredientDetailInfo } from "../responses/ingredientDetailInfo";
import { IngredientInfo } from "../responses/ingredientInfo";
import { OfflineMartInfo } from "../responses/offlineMartInfo";
import { RecipeDetailInfo } from "../responses/recipeDetailInfo";
import { RecipeInfo } from "../responses/recipeInfo";
import { RecipeOrderInfo } from "../responses/recipeOrderInfo";
import { YoutubeInfo } from "../responses/youtubeInfo";
import { SearchResult } from "../responses/searchResult";
import { MyRecipeInfo } from "../responses/myRecipeInfo";
import { MyRecipeDetailInfo } from "../responses/myRecipeDetailInfo";
import { MyRecipeIngredientInfo } from "../responses/myRecipeIngredientInfo";
import { LimitPriceNoticeInfo } from "../responses/limitPriceNoticeInfo";
import { NotificationInfo } from "../responses/notificationInfo";

export const getIngredientList: IngredientInfo[] = Array.from({ length: 100 }, (_, i) => i).map(
  (it) => ({
    ingredient_id: it + 1,
    name: `사과 ${it + 1}`,
    price: Math.ceil(10000 * Math.random()),
    unit: "kg",
    quantity: 1,
    volatility: Math.ceil(100 * Math.random() * (Math.random() > 0.5 ? 1 : -1)),
    allergy: Math.random() > 0.5,
    bookmark: Math.random() > 0.5,
    basket: Math.random() > 0.5,
    high_class_id: Math.ceil(Math.random() * 10),
    high_class_name: "이름",
    views: 10000,
  })
);

export const getBookmarkIngredientList: IngredientInfo[] = Array.from(
  { length: 79 },
  (_, i) => i
).map((it) => ({
  ingredient_id: 1,
  name: `사과 ${it + 1}`,
  price: Math.ceil(10000 * Math.random()),
  unit: "kg",
  quantity: 1,
  volatility: Math.ceil(100 * Math.random() * (Math.random() > 0.5 ? 1 : -1)),
  allergy: Math.random() > 0.5,
  bookmark: true,
  basket: Math.random() > 0.5,
  high_class_id: Math.ceil(Math.random() * 10),
  high_class_name: "이름",
  views: 10000,
}));

export const getIngredientDetailInfo: IngredientDetailInfo = {
  ingredient_info: {
    views: 10000,
    ingredient_id: 5,
    name: "빵또아",
    price: 2000,
    unit: "개",
    quantity: 1,
    volatility: 100,
    allergy: false,
    bookmark: true,
    basket: false,
    high_class_id: Math.ceil(Math.random() * 10),
    high_class_name: "이름",
  },
  price_transition_info: {
    before_price: 1000,
    price: 2000,
    wholesales: {
      daily: [
        {
          date: "2022-09-12",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-14",
          price: 150,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-15",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-16",
          price: 300,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-17",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-18",
          price: 200,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-19",
          price: 150,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-20",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-21",
          price: 300,
          unit: "개",
          quantity: 1,
        },
      ],
      monthly: [
        {
          date: "2022-08-13",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
      ],
      yearly: [
        {
          date: "2021-09-13",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
      ],
    },
    retailsales: {
      daily: [
        {
          date: "2022-09-12",
          price: 500,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-14",
          price: 750,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-15",
          price: 400,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-16",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-17",
          price: 400,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-18",
          price: 400,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-19",
          price: 300,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-20",
          price: 700,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-21",
          price: 500,
          unit: "개",
          quantity: 1,
        },
      ],
      monthly: [
        {
          date: "2022-08-13",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 2000,
          unit: "개",
          quantity: 1,
        },
      ],
      yearly: [
        {
          date: "2021-09-13",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 2000,
          unit: "개",
          quantity: 1,
        },
      ],
    },
    pastvol: 3,
    todayvol: 2,
  },
  online_mart_info: [
    {
      image_path: "https://img.danawa.com/cmpny_info/images/ED910_logo.gif",
      mall_name: "인터파크",
      name: "보라보라보라보라보라보라보라보라보라보라보라색 가지",
      price: 20200,
      url: "https://shopping.interpark.com/product/productInfo.do?prdNo=8519195204&dispNo=016001&bizCd=P01415&utm_medium=affiliate&utm_source=danawa&utm_campaign=shop_20211015_danawa_p01415_cps&utm_content=conversion_47",
    },
    {
      image_path: "https://img.danawa.com/cmpny_info/images/TH201_logo.gif",
      mall_name: "11번가",
      name: "진짜 가지",
      price: 20800,
      url: "https://www.11st.co.kr/products/4564092401?service_id=pcdn&utm_term=&utm_campaign=%B4%D9%B3%AA%BF%CDpc_%B0%A1%B0%DD%BA%F1%B1%B3%B1%E2%BA%BB&utm_source=%B4%D9%B3%AA%BF%CD_PC_PCS&utm_medium=%B0%A1%B0%DD%BA%F1%B1%B3",
    },
    {
      image_path: "https://img.danawa.com/cmpny_info/images/EE128_logo.gif",
      mall_name: "G마켓",
      name: "가지무침이 되기싫은가지",
      price: 22000,
      url: "http://item.gmarket.co.kr/DetailView/Item.asp?goodscode=2475349781&GoodsSale=Y&jaehuid=200002657&service_id=pcdn",
    },
  ],
};

export const getIngredientHighClassList: HighClass[] = [
  {
    id: 1,
    name: "곡식류",
  },
  {
    id: 2,
    name: "과일류",
  },
  {
    id: 3,
    name: "정육",
  },
  {
    id: 4,
    name: "과자류",
  },
  {
    id: 5,
    name: "빙과류",
  },
  {
    id: 6,
    name: "베이커리류",
  },
  {
    id: 7,
    name: "몰류",
  },
  {
    id: 8,
    name: "삼류",
  },
  {
    id: 9,
    name: "오류",
  },
  {
    id: 10,
    name: "하류",
  },
];

export const getOfflineMartList: OfflineMartInfo[] = [
  {
    store_id: 1,
    name: "자유시장",
    price: 1000,
    latitude: 37.4834,
    longitude: 126.7804,
    distance: 100,
  },
  {
    store_id: 2,
    name: "이마트",
    price: 1000,
    latitude: 37.4842,
    longitude: 126.7823,
    distance: 200,
  },
];

export const getBasketIngredientList: IngredientInfo[] = [
  {
    ingredient_id: 1,
    name: "보리",
    price: 2500,
    unit: "kg",
    quantity: 1,
    volatility: 20,
    allergy: false,
    bookmark: true,
    basket: true,
    high_class_id: 1,
    high_class_name: "곡식",
    views: 10000,
  },
  {
    ingredient_id: 2,
    name: "고구마",
    price: 1000,
    unit: "kg",
    quantity: 1,
    volatility: -20,
    allergy: false,
    bookmark: true,
    basket: true,
    high_class_id: 1,
    high_class_name: "곡식",
    views: 10000,
  },
  {
    ingredient_id: 3,
    name: "고추",
    price: 2000,
    unit: "kg",
    quantity: 1,
    volatility: -80,
    allergy: false,
    bookmark: true,
    basket: true,
    high_class_id: 1,
    high_class_name: "곡식",
    views: 10000,
  },
  {
    ingredient_id: 4,
    name: "초코파이",
    price: 2000,
    unit: "kg",
    quantity: 1,
    volatility: 0,
    allergy: false,
    bookmark: true,
    basket: true,
    high_class_id: 1,
    high_class_name: "곡식",
    views: 10000,
  },
  {
    ingredient_id: 5,
    name: "빵또아",
    price: 2000,
    unit: "kg",
    quantity: 1,
    volatility: 60,
    allergy: false,
    bookmark: true,
    basket: true,
    high_class_id: 1,
    high_class_name: "곡식",
    views: 10000,
  },
];

export const getRecipeList: RecipeInfo[] = Array.from({ length: 88 }, (_, i) => i).map((it) => ({
  recipe_id: it + 1,
  image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
  name: `가지무침 ${it + 1}`,
  desc: "맛있는 가지무침~~~~~",
  views: 10000,
  bookmark: true,
}));

export const getRecipeDetailInfo: RecipeDetailInfo = {
  recipe_info: {
    views: 10000,
    recipe_id: 1,
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "가지무침",
    desc: "맛있는 가지무침~~~~~",
    bookmark: true,
  },
  ingredient_list: [
    {
      ingredient_id: 1,
      name: "사과",
      price: 1000,
      unit: "kg",
      quantity: 1,
      volatility: 20,
      allergy: false,
      bookmark: false,
      basket: false,
      high_class_id: 1,
      high_class_name: "곡식",
      views: 10000,
    },
    {
      ingredient_id: 2,
      name: "가지",
      price: 1500,
      unit: "kg",
      quantity: 1,
      volatility: 20,
      allergy: true,
      bookmark: false,
      basket: false,
      high_class_id: 1,
      high_class_name: "곡식",
      views: 10000,
    },
  ],
  extra_ingredient_list: ["간장", "소금", "참기름"],
  youtube_list: [
    {
      thumbnail_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
      name: "맛있는 가지무침 무작정 따라하기",
      channel_name: "가지무침장인",
      view: 10000000,
      date: "3일 전",
      url: "https://www.youtube.com/watch?v=Ujjdn2wMIew&list=PLZKTXPmaJk8Lx3TqPlcEAzTL8zcpBz7NP&index=1",
    },
  ],
};

export const getRecipeOrderList: RecipeOrderInfo[] = [
  {
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    description: "가지를 맛있게 손질하기",
  },
  {
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    description: "가지를 맛있게 볶기",
  },
  {
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    description: "가지를 맛있게 쳐다보기",
  },
  {
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    description: "가지를 냠냠 먹기",
  },
];

export const getPopularYoutubeList: YoutubeInfo[] = [
  {
    thumbnail_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "맛있는 가지무침 무작정 따라하기1",
    channel_name: "가지무침장인",
    view: 10000000,
    date: "3일 전",
    url: "https://www.youtube.com/watch?v=Ujjdn2wMIew&list=PLZKTXPmaJk8Lx3TqPlcEAzTL8zcpBz7NP&index=1",
  },
  {
    thumbnail_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "맛있는 가지무침 무작정 따라하기2",
    channel_name: "가지무침장인",
    view: 10000000,
    date: "3일 전",
    url: "https://www.youtube.com/watch?v=Ujjdn2wMIew&list=PLZKTXPmaJk8Lx3TqPlcEAzTL8zcpBz7NP&index=1",
  },
  {
    thumbnail_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "맛있는 가지무침 무작정 따라하기3",
    channel_name: "가지무침장인",
    view: 10000000,
    date: "3일 전",
    url: "https://www.youtube.com/watch?v=Ujjdn2wMIew&list=PLZKTXPmaJk8Lx3TqPlcEAzTL8zcpBz7NP&index=1",
  },
  {
    thumbnail_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "맛있는 가지무침 무작정 따라하기4",
    channel_name: "가지무침장인",
    view: 10000000,
    date: "3일 전",
    url: "https://www.youtube.com/watch?v=Ujjdn2wMIew&list=PLZKTXPmaJk8Lx3TqPlcEAzTL8zcpBz7NP&index=1",
  },
];

export function search(keyword: string): SearchResult {
  if (keyword === "결과없음") {
    return {
      ingredient_list: [],
      recipe_list: [],
    };
  }

  const ingredient_list = getIngredientList;
  ingredient_list.forEach((v, i) => (v.name = `${keyword} ${i + 1}`));
  const recipe_list = getRecipeList;
  recipe_list.forEach((v, i) => (v.name = `${keyword} ${i + 1}`));

  return {
    ingredient_list,
    recipe_list,
  };
}

export const getMyRecipeList: MyRecipeInfo[] = [
  {
    my_recipe_id: 1,
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "콩나물 무침",
  },
  {
    my_recipe_id: 2,
    image_path: "https://i.ytimg.com/vi/6aZjI0hgEN0/maxresdefault.jpg",
    name: "도토리묵사발",
  },
];

export const getMyRecipeDetailInfo: MyRecipeDetailInfo = {
  total_price: 38000,
  ingredient_list: getIngredientList,
  price_transition_info: {
    before_price: 1000,
    price: 2000,
    wholesales: {
      daily: [
        {
          date: "2022-09-12",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
      ],
      monthly: [
        {
          date: "2022-08-13",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
      ],
      yearly: [
        {
          date: "2021-09-13",
          price: 100,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 200,
          unit: "개",
          quantity: 1,
        },
      ],
    },
    retailsales: {
      daily: [
        {
          date: "2022-09-12",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 2000,
          unit: "개",
          quantity: 1,
        },
      ],
      monthly: [
        {
          date: "2022-08-13",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 2000,
          unit: "개",
          quantity: 1,
        },
      ],
      yearly: [
        {
          date: "2021-09-13",
          price: 1000,
          unit: "개",
          quantity: 1,
        },
        {
          date: "2022-09-13",
          price: 2000,
          unit: "개",
          quantity: 1,
        },
      ],
    },
    pastvol: -2,
    todayvol: 1,
  },
  image_path:
    "https://gamulbucket2022.s3.ap-northeast-2.amazonaws.com/myRecipe/031b4b72-bf30-4e0f-af45-413dd89c6531.png",
  name: "윤선이의 된장찌개",
};

export const getMyRecipeIngredientList: MyRecipeIngredientInfo[] = [
  { ingredient_id: 1, quantity: 0.5 },
  { ingredient_id: 2, quantity: 1 },
  { ingredient_id: 3, quantity: 2 },
];

export const getAllergyIngredientList: number[] = [1, 2, 3];

export const getLimitPriceList: LimitPriceNoticeInfo[] = [
  {
    ingredient_id: 1,
    upper_limit_price: 2000,
    lower_limit_price: 800,
  },
  {
    ingredient_id: 2,
    upper_limit_price: 7000,
    lower_limit_price: 80,
  },
  {
    ingredient_id: 3,
    upper_limit_price: 20000,
    lower_limit_price: 8,
  },
];

export const getNotificationItemList: NotificationInfo[] = Array.from(
  { length: 50 },
  (_, i) => i
).map((it) => ({
  ingredient_id: it + 1,
  title: "고추 상한가 알림",
  message: "고추가 1,000원/100g 까지 떨어졌어요! 지금이 바로 기회!!",
}));
