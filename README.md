## Query and Mutation Script of GraphQL

- Get All
```graphql
query {
  allEmployee {
    id
    name
  }
}
```

- Get By Id
```graphql
query {
  employeeById (id: "1") {
    id
    name
  }
}
```

- Add Employee
```graphql
mutation {
  addEmployee (id: "6", name:"Santoriyu") {
    id
    name
  }
}
```

- Update Employee
```graphql
mutation {
  updateEmployee (id: "1", name:"Asep Saputra") {
    id
    name
  }
}
```

- Delete Employee
```graphql
mutation {
  deleteEmployee (id:"1")
}
```

