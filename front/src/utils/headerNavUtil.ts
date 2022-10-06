const navBarIndexArr = [
  ["", "notice", "signup"],
  ["ingredient-info", "store-info", "ingredient"],
  ["basket"],
  ["recipe-detail", "recipe-info", "recipe"],
  [
    "my-recipe-info",
    "login",
    "my-info",
    "my-recipe",
    "register-alarm",
    "register-allergy",
    "register-my-recipe",
    "wish-list",
  ],
];

const hideHeaderBarArr = [
  "notice",
  "ingredient-info",
  "store-info",
  "recipe-info",
  "recipe-detail",
  "signup",
  "register-allergy",
  "wish-list",
  "my-recipe",
  "my-recipe-info",
  "register-my-recipe",
  "register-alarm",
];

const hideNavBarArr = ["signup"];

export const showHeaderBar = (pathname: string) => {
  const pathnameSplited = pathname.split("/");
  if (pathnameSplited.length === 1) {
    return true;
  }

  const idx = hideHeaderBarArr.findIndex((v) => v === pathnameSplited[1]);
  return 0 <= idx ? false : true;
};

export const showNavBar = (pathname: string) => {
  const pathnameSplited = pathname.split("/");
  if (pathnameSplited.length === 1) {
    return true;
  }

  const idx = hideNavBarArr.findIndex((v) => v === pathnameSplited[1]);
  return 0 <= idx ? false : true;
} 

export const getNavBarActiveIndex = (pathname: string) => {
  const pathnameSplited = pathname.split("/");
  if (pathnameSplited.length === 1) {
    return 0;
  }

  for (let i = 0; i < navBarIndexArr.length; i++) {
    const idx = navBarIndexArr[i].findIndex((v) => v === pathnameSplited[1]);
    if (-1 < idx) {
      return i;
    }
  }
  return 0;
};
