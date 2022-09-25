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
  ""
];

export const showHeaderBar = (pathname: string) => {
  const idx = getNavBarActiveIndex(pathname);
  return 0 <= idx ? true : false;
};

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
