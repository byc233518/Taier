package com.dtstack.rdos.engine.execution.flink120.sink;

import com.dtstack.rdos.commom.exception.RdosException;
import com.dtstack.rdos.engine.execution.base.enumeration.ESinkType;
import com.dtstack.rdos.engine.execution.base.operator.CreateResultOperator;
import com.dtstack.rdos.engine.execution.flink120.sink.csv.RdosCsvSink;
import com.dtstack.rdos.engine.execution.flink120.sink.db.mysql.MysqlSink;
import com.dtstack.rdos.engine.execution.flink120.sink.kafka.RdosKafka09Sink;
import org.apache.flink.table.sinks.TableSink;

/**
 * Reason:
 * Date: 2017/3/10
 * Company: www.dtstack.com
 *
 * @ahthor xuchao
 */

public class SinkFactory {

    public static TableSink getTableSink(CreateResultOperator resultOperator){

        String resultType = resultOperator.getType();
        ESinkType sinkType = ESinkType.getSinkType(resultType);

        switch (sinkType){
            case MYSQL:
                return new MysqlSink(resultOperator);

            case CSV://FIXME 未测试
                return new RdosCsvSink(resultOperator);

            case KAFKA09://FIXME 未测试
                return new RdosKafka09Sink().genStreamSource(resultOperator);
        }

        throw new RdosException("not support sink type:" + resultType + "!!!");
    }


}