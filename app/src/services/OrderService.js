import http from "../http-common";

const getAllOrders = () => {
  return http.get("/order");
};

const getOrder = (id) => {
  return http.get(`/order/${id}`);
};

const create = (data) => {
  return http.post("/order", data);
};

const searchOrder = (param) => {
  return http.post(`/order/search?param=${param}`);
};

const getAllProducts = () => {
  return http.get("/product");
};

const createItems = (data) => {
  return http.post("/orderitems", data);
};

const searchProduct = (param) => {
  return http.post(`/product/search?param=${param}`);
};

const OrderService = {
  getAllOrders,
  getOrder,
  create,
  searchOrder,
  getAllProducts,
  createItems,
  searchProduct,
};

export default OrderService;
