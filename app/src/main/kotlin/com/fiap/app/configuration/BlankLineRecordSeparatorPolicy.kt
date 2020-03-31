package com.fiap.app.configuration

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy

class BlankLineRecordSeparatorPolicy : SimpleRecordSeparatorPolicy() {
    override
    fun isEndOfRecord(line: String): Boolean {
        return line.trim().length != 0 && super.isEndOfRecord(line)
    }

    override
    fun postProcess(record: String): String {
        return if (record == null || record.trim().length == 0) {
            ""
        } else super.postProcess(record)
    }
}
