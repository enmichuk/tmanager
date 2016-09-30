Design and implementation of a RESTful API (including data model and the backing implementation) for money transfers between internal users/accounts

Account creation:
```
POST /account
{
    "id": "124",
    "owner": "Eugen",
    "balance": 0
}
```

Get account by id
```
GET /account/id
```

Get all accounts
```
GET /accounts
```

Transaction:
```
POST /transaction
{
    "transfer": {
    	"from": "123",
    	"to": "124",
    	"amount": 120
    }
}
```

Get all transfers:
```
GET /transfers
```
