import { useEffect, useState } from "react";
import { SearchType } from "../utils/localStorageUtil";

export function useSearchLocalStorage<T>(
  searchType: SearchType,
  filter: (item: T) => boolean
): T[] {
  const [value, setValue] = useState<T[]>([]);
  useEffect(() => {
    const item = localStorage.getItem(searchType);
    if (item) {
      const origin = JSON.parse(item) as T[];
      setValue(origin.filter(filter));
    }
  }, [filter, searchType, value]);

  return value;
}