DROP TABLE IF EXISTS scheduled_transfers;

CREATE TABLE scheduled_transfers
(
    id UUID(36) PRIMARY KEY,
    transfer_date       DATE           NOT NULL,
    amount              decimal(15, 2) NOT NULL,
    to_account_number   VARCHAR(10)    NOT NULL,
    from_account_number VARCHAR(10)    NOT NULL,
    memo                VARCHAR(255) DEFAULT NULL
);