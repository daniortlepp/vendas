import React, { useState, useEffect, useMemo, useRef } from "react";
import OrderDataService from "../services/OrderService";
import { useTable } from "react-table";
import { Table } from 'react-bootstrap';
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import "bootstrap/dist/css/bootstrap.min.css";
import * as ReactBootStrap from "react-bootstrap";
import { withRouter } from "react-router-dom";

const AddOrder = (props) => {
  const [searchField, setSearchField] = useState("");
  const [orders, setOrders] = useState([]);
  const ordersRef = useRef();
  ordersRef.current = orders;
  const [show, setShow] = useState(false);
  const [customerName, setCustomerName] = useState('')

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const initialOrderState = {
    id: null,
    customerName: "",
    total: 0.00
  };
  const [order, setOrder] = useState(initialOrderState);
  const [orderItems, setOrderItems] = useState([]);

  const [products, setProducts] = useState([]);

  const handleChange = (e) => {
    setOrder({
      ...order,

      [e.target.name]: e.target.value
    });
  };

  const formatCurrency = (vl) => {
    vl = parseFloat(vl);
    vl = vl.toFixed(2);
    vl = vl.toString().replace(".", ",");
    return 'R$ ' + vl;
  }

  useEffect(() => {
    refreshOrder();
    retrieveProducts();
  }, [orderItems]);

  const onChangeSearchField = (e) => {
    const searchField = e.target.value;
    setSearchField(searchField);
  };

  const searchProduct = () => {
    OrderDataService.searchProduct(searchField)
      .then((response) => {
        setProducts(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const retrieveProducts = () => {
    OrderDataService.getAllProducts()
      .then((response) => {
        let respondeData = response.data;
        let productsList = [];
        respondeData.forEach(item => {
            productsList.push(item);
        });
        setProducts(productsList);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshOrder = () => {
    setOrder(order);
    setOrderItems(orderItems);
  };


  const handleInputChange = event => {
    const { name, value } = event.target;
    setOrder({ ...order, [name]: value });
  };

  const saveOrder = () => {
    OrderDataService.create(order)
      .then(response => {
        let orderCreated = response.data;

        orderItems.forEach(item => {
          let orderitem = {
            "order": orderCreated,
            "product": {"id": item.product.id, "name": item.product.name, "price": item.product.price},
            "unitPrice": item.unitPrice,
            "totalPrice": item.totalPrice,
            "quantity": item.quantity
          };

          OrderDataService.createItems(orderitem)
            .then(response => {
            })
            .catch(e => {
              console.log(e);
            });
        });

        window.location.href = './order';
      })
      .catch(e => {
        console.log(e);
      });
  };

  const newOrder = () => {
    setOrder(initialOrderState);
  };

  const addItem = (id, name, price) => {
    var quantity = document.getElementById('quantity' + id).value;
    orderItems.push({
      "product": {"id": id, "name": name, "price": price},
      "unitPrice": price,
      "totalPrice": price * quantity,
      "quantity": quantity
    });
    setOrderItems(orderItems);

    var totalOrder = 0;
    orderItems.forEach(item => {
      totalOrder += item.totalPrice;
    });
    order.total = totalOrder;

    refreshOrder();
    retrieveProducts();
  }

  const columns = useMemo(
    () => [
      {
        Header: "Id",
        accessor: "product.id",
      },
      {
        Header: "Nome",
        accessor: "product.name",
      },
      {
        Header: "Valor",
        accessor: "unitPrice",
      },
      {
        Header: "Quantidade",
        accessor: "quantity",
      },
      {
        Header: "Total",
        accessor: "totalPrice",
      },
    ],
    []
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({
    columns,
    data: orderItems,
  });

  const columnsProds = [
    {
      Header: 'ID',
      accessor: 'id'
    },
    {
      Header: 'Nome',
      accessor: 'name'
    }, 
    {
      Header: 'Valor',
      accessor: 'price'
    }
  ]

  return (
     <>
   <div className="mb-3">
      {(
        <div>
          <div className="form-group">
            <label htmlFor="customerName">Nome do cliente</label>
            <input
              type="text"
              className="form-control"
              id="customerName"
              required
              onChange={handleInputChange}
              value={order.customerName}
              name="customerName"
            />
          </div>

      <button onClick={handleShow} className="btn btn-success" style={{marginTop: '20px'}}>
        Adicionar produto
      </button>
      <div className="col-md-12 list">
        <table
          className="table table-striped table-bordered"
          id="tableItems"
          {...getTableProps()}
        >
          <thead>
            {headerGroups.map((headerGroup) => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map((column) => (
                  <th {...column.getHeaderProps()} style={{textAlign: 'center'}}>
                    {column.render("Header")}
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map((cell) => {
                    return (
                      <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                    );
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>

      <p>Total: {order.total}</p>

          <button onClick={saveOrder} className="btn btn-success">
            Salvar
          </button>
        </div>
      )}
    </div>


    <Modal
        show={show}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
        size="lg"
      >
        <Modal.Header closeButton>
          <Modal.Title>Adicionar produto</Modal.Title>
        </Modal.Header>
        <Modal.Body>

        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Filtrar produto"
            value={searchField}
            onChange={onChangeSearchField}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={searchProduct}
            >
              Filtrar
            </button>
          </div>
        </div>

          <div className="col-md-12 list">
            <ReactBootStrap.Table striped bordered hover>
              <thead>
                <tr>
                  <th style={{textAlign: 'center'}}>ID</th>
                  <th style={{textAlign: 'center'}}>Nome</th>
                  <th style={{textAlign: 'center'}}>Valor</th>
                  <th style={{textAlign: 'center'}}>Quantidade</th>
                  <th style={{textAlign: 'center'}}></th>
                </tr>
              </thead>
              <tbody>
                {products.map((item) => (
                    <tr key={item.id}>
                      <td style={{textAlign: 'center'}}>{item.id}</td>
                      <td>{item.name}</td>
                      <td style={{textAlign: 'right'}}>{formatCurrency(item.price)}</td>
                      <td style={{textAlign: 'center'}}><input id={"quantity"+item.id} name={"quantity"+item.id} type="text" className="form-control" style={{width: '100px'}}/></td>
                      <td style={{textAlign: 'center'}}><Button variant="primary" onClick={() => addItem(item.id, item.name, item.price)}>Adicionar</Button></td>
                    </tr>
                  ))}
              </tbody>
            </ReactBootStrap.Table>
          </div>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Fechar
          </Button>
        </Modal.Footer>
      </Modal>
      </>
  );
};

export default AddOrder;
