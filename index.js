import { NativeModules } from "react-native";

const { CreateHmac: CreateHmacModules } = NativeModules;

export const createHmacSHA1 = async (secretKey, data = {}) => {
  return CreateHmacModules.createHmacSHA1(secretKey, data);
};

export default {
  createHmacSHA1
};
