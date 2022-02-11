import React, { useState, useEffect, useMemo, useRef } from "react";
import OrderDataService from "../services/OrderService";
import { useTable } from "react-table";

const OrdersList = (props) => {
  const [orders, setOrders] = useState([]);
  const [searchField, setSearchField] = useState("");
  const ordersRef = useRef();

  ordersRef.current = orders;

  useEffect(() => {
    retrieveOrders();
  }, []);

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

  const onChangeSearchField = (e) => {
    const searchField = e.target.value;
    setSearchField(searchField);
  };

  const retrieveOrders = () => {
    OrderDataService.getAllOrders()
      .then((response) => {
        let respondeData = response.data;
        let orderList = [];
        respondeData.forEach(item => {
            item.date = formatDate(item.date);
            item.total = formatCurrency(item.total);
            orderList.push(item);
        });
        setOrders(orderList);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrieveOrders();
  };

  const searchOrder = () => {
    OrderDataService.searchOrder(searchField)
      .then((response) => {
        setOrders(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const openOrder = (rowIndex) => {
    const id = ordersRef.current[rowIndex].id;

    props.history.push("/order/" + id);
  };

  const columns = useMemo(
    () => [
      {
        Header: "Número",
        accessor: "id",
        Cell: row => <div style={{ textAlign: "center" }}>{row.value}</div>
      },
      {
        Header: "Cliente",
        accessor: "customerName",
      },
      {
        Header: "Valor",
        accessor: "total",
        Cell: row => <div style={{ textAlign: "right" }}>{row.value}</div>
      },
      {
        Header: "Data",
        accessor: "date",
        Cell: row => <div style={{ textAlign: "center" }}>{row.value}</div>
      },
      {
        Header: "",
        accessor: "actions",
        Cell: (props) => {
          const rowIdx = props.row.id;
          return (
            <div style={{ textAlign: "center" }}>
              <span onClick={() => openOrder(rowIdx)}>
                Detalhes
              </span>

            </div>
          );
        },
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
    data: orders,
  });

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Buscar pedidos"
            value={searchField}
            onChange={onChangeSearchField}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={searchOrder}
            >
              Buscar
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-12 list">
        <table
          className="table table-striped table-bordered"
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

    </div>
  );
};

export default OrdersList;
