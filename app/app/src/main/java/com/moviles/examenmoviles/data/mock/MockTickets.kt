package com.moviles.examenmoviles.data.mock

import com.moviles.examenmoviles.domain.model.*

object MockTickets {

    val tickets = mutableListOf(
        Ticket(
            id = 1,
            title = "Missing sticker packs — Zone 3 CR",
            description = "Distributor in Zone 3 Costa Rica reported 200 missing sticker packs from the last shipment. Inventory does not match the delivery receipt. Urgent resolution needed before weekend peak sales.",
            priority = Priority.HIGH,
            status = Status.OPEN,
            supplier = "Distribuidora Centroamérica S.A.",
            category = Category.INVENTORY,
            createdAt = "2026-05-28"
        ),
        Ticket(
            id = 2,
            title = "Delayed shipment — San José depot",
            description = "Shipment #SJ-2026-441 scheduled for May 25 has not arrived at the San José central depot. Carrier has not provided tracking updates in 48 hours.",
            priority = Priority.HIGH,
            status = Status.IN_PROGRESS,
            supplier = "LogiRapid Express",
            category = Category.DISTRIBUTION,
            createdAt = "2026-05-27"
        ),
        Ticket(
            id = 3,
            title = "Damaged sticker boxes — Heredia branch",
            description = "15 boxes arrived with water damage at Heredia branch. Product is unsellable. Photos submitted. Replacement request pending approval from regional manager.",
            priority = Priority.MEDIUM,
            status = Status.OPEN,
            supplier = "Panini Italy HQ",
            category = Category.SUPPLIER,
            createdAt = "2026-05-26"
        ),
        Ticket(
            id = 4,
            title = "Wrong album version — Cartago point of sale",
            description = "Point of sale in Cartago received the European album version instead of the CONCACAF edition. 300 units affected. Customers are returning product.",
            priority = Priority.HIGH,
            status = Status.OPEN,
            supplier = "Distribuidora Centroamérica S.A.",
            category = Category.LOGISTICS,
            createdAt = "2026-05-25"
        ),
        Ticket(
            id = 5,
            title = "Inventory count mismatch — Alajuela warehouse",
            description = "System shows 1,500 units in stock but physical count at Alajuela warehouse shows only 1,200. Discrepancy of 300 units under investigation.",
            priority = Priority.MEDIUM,
            status = Status.IN_PROGRESS,
            supplier = "Almacenes Nacionales CR",
            category = Category.INVENTORY,
            createdAt = "2026-05-24"
        ),
        Ticket(
            id = 6,
            title = "Supplier invoice discrepancy — May batch",
            description = "Invoice from supplier does not match the agreed pricing for the May distribution batch. Overcharge of $2,400 detected. Finance team notified.",
            priority = Priority.LOW,
            status = Status.RESOLVED,
            supplier = "Panini Italy HQ",
            category = Category.SUPPLIER,
            createdAt = "2026-05-20"
        )
    )
}