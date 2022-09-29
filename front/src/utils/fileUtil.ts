export const convertFileToBase64 = (file: File, callback: (base64: any) => void) => {
  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onloadend = () => {
    callback(reader.result);
  };
};
