-- Create customer profiles upon application initialization
INSERT INTO customer_profiles (id, first_name, last_name, address, bvn, created_at, updated_at)
VALUES ('ab1f8512-b620-4e37-b7bb-c226b0c23671', 'Ajax', 'Peter', '5 NowayHome Street', '9737494739',
        '2023-07-02 23:03:38.749', '2023-07-02 23:03:38.749'),
       ('b6d75818-2fbd-46f4-81cd-5c783a95a9b9', 'Prince', 'Adii', '90 Ill Street', '12342234432',
        '2023-07-02 23:03:38.749', '2023-07-02 23:03:38.749'),
       ('ecda1a9a-eebe-4615-9aa8-db2c8e07d67f', 'John', 'Tella', '90th Street Estate', '93842038493',
        '2023-07-02 23:03:38.749', '2023-07-02 23:03:38.749'),
       ('03841a2e-c080-43b7-bb19-13769810ca57', 'Zedd', 'Leman', '50 Pivot Street', '938048482930',
        '2023-07-02 23:03:38.749', '2023-07-02 23:03:38.749');