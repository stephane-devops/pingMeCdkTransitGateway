package com.stephanecharron.stack;

import com.stephanecharron.stackProps.VpcListStackProps;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.*;
import software.constructs.Construct;

import java.util.stream.Collectors;

public class TransitGatewayStack extends Stack {
    public TransitGatewayStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    @lombok.Builder
    public TransitGatewayStack(final Construct scope, final String id, final VpcListStackProps vpcListStackProps) {
        super(scope, id, vpcListStackProps);
        CfnTransitGateway tgw = CfnTransitGateway.Builder.create(this,"Tgw").build();

        int index = 0;
        for(Vpc vpc: vpcListStackProps.getVpcs()){
            CfnTransitGatewayAttachment.Builder.create(this, "TgwVpcAttachment_"+index)
                    .vpcId(vpc.getVpcId())
                    .subnetIds(vpc.getPrivateSubnets()
                            .stream()
                            .map(ISubnet::getSubnetId)
                            .collect(Collectors.toList()))
                    .transitGatewayId(tgw.getRef())
                    .build();
            index++;
        }

        CfnOutput.Builder.create(this, "TransitGatewayId")
                .value(tgw.getRef())
                .exportName("TransitGatewayId")
                .build();
    }
}
