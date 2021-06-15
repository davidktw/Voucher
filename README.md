# Voucher
Just a demonstration project

## How to build
```bash
./gradlew clean
./gradlew build
```

## How to run web project
```bash
./gradlew bootrun
```

## How to access H2 database console
Open browser at `http://localhost:8080/h2-console`
Login using credentials `username=demo`, `password=demo`

## Objective to this demonstration
It is to demonstrate how to use Pessimistic lock on a locking table
to achieve consistent testing of number of records before insertion
in an atomic transactional manner

### How to test
1. Start up the web application. Refer to [How to run web project](#how-to-run-web-project).
2. Open the browser and login to the h2 database console.
3. Select all rows from the voucher table. It should shows empty rows.
4. Using a terminal shell, run the following codes:
   ```bash
   for i in `seq 1 1000`; do \
     curl -s http://localhost:8080/voucher/request?userid=$[ $RANDOM % 100 ] >/dev/null & \
   done
   ```
   This will ensure there are 1000 concurrent processes trying to obtain vouchers from the system.
5. Once step (4) is done, use the h2 database console, run the following query to find out
   the number of vouchers requested for each user.
   ```sql
   select user_id, count(*) from voucher group by user_id;
   ```
   It should shows that each user(user_id) should have at most 2 vouchers issued.
