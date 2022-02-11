import React, { useState, useEffect, useMemo, useRef } from "react";
import { useTable } from "react-table";
import OrderDataService from "../services/OrderService";

import { Table } from 'react-bootstrap';
import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import * as ReactBootStrap from "react-bootstrap";

const Order = props => {
  const initialOrderState = {
    id: null,
    customerName: "",
    total: 0.00,
    items: []
  };
  const [currentOrder, setCurrentOrder] = useState(initialOrderState);
  const [orderItems, setOrderItems] = useState([]);

  const formatDate = (dateStr) => {
    let [year, month, day] = dateStr.split('-');
    day = Number(day) + 1;
    day = (day.toString().length==1?'0' + day:day);
    let newDate = `${day}/${month}/${year}`;
    return newDate;
  };

  const formatCurrency = (vl) => {
    vl = vl.toFixed(2);
    vl = vl.toString().replace(".", ",");
    return 'R$ ' + vl;
  }

  const getOrder = id => {
    OrderDataService.getOrder(id)
      .then(response => {
        let respondeData = response.data;
        respondeData.date = formatDate(respondeData.date);
        respondeData.total = formatCurrency(respondeData.total);
        setCurrentOrder(respondeData);
        let respondeDataItems =  respondeData.items;
        respondeDataItems.forEach(item => {
            if (orderItems.length < respondeDataItems.length) {
              item.unitPrice = formatCurrency(item.unitPrice);
              item.totalPrice = formatCurrency(item.totalPrice);
              orderItems.push(item);
            }
        });
        setOrderItems(orderItems);
     })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getOrder(props.match.params.id);
  }, [props.match.params.id]);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setCurrentOrder({ ...currentOrder, [name]: value });
  };

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

  return (
    <div>
      {currentOrder ? (
        <div className="mb-3">
          <h4>Pedido número {currentOrder.id}</h4>
            <p></p>
            <p>Nome do cliente: {currentOrder.customerName}</p>
            <p>Data: {currentOrder.date}</p>

            <div className="col-md-12 list" onClick={getOrder(currentOrder.id)}>
              <ReactBootStrap.Table striped bordered hover>
                <thead>
                  <tr>
                    <th style={{textAlign: 'center'}}>ID</th>
                    <th style={{textAlign: 'center'}}>Nome</th>
                    <th style={{textAlign: 'center'}}>Valor</th>
                    <th style={{textAlign: 'center'}}>Quantidade</th>
                    <th style={{textAlign: 'center'}}>Total</th>
                  </tr>
                </thead>
                <tbody>
                  {orderItems.map((item) => (
                      <tr key={item.id}>
                        <td style={{textAlign: 'center'}}>{item.product.id}</td>
                        <td>{item.product.name}</td>
                        <td style={{textAlign: 'right'}}>{item.unitPrice}</td>
                        <td style={{textAlign: 'center'}}>{item.quantity}</td>
                        <td style={{textAlign: 'center'}}>{item.totalPrice}</td>
                      </tr>
                    ))}
                </tbody>
              </ReactBootStrap.Table>
            </div>

            <p>Total: {currentOrder.total}</p>


        </div>
      ) : (
        <div>
          <br />
          <p>Please click on a Order...</p>
        </div>
      )}
    </div>
  );
};

export default Order;
