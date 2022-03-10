aws ssm send-command \
--document-name "AWS-RunShellScript" \
--document-version "1" \
--targets '[{"Key":"InstanceIds","Values":["i-098b005b6dd83c586"]}]' \
--parameters '{"workingDirectory":[""],"executionTimeout":["3600"],"commands":["ping 10.0.1.13 -c 3"]}' \
--timeout-seconds 600 \
--max-concurrency "50" \
--max-errors "0" \
{
    "Command": {
        "CommandId": "e2171883-d9d1-478c-9ad2-2c7c51ca6c2e",
        "DocumentName": "AWS-RunShellScript",
        "DocumentVersion": "1",
        "Comment": "",
        "ExpiresAfter": "2020-11-23T22:04:17.410000+01:00",
        "Parameters": {
            "commands": [
                "ping 10.0.1.13 -c 3"
            ],
            "executionTimeout": [
                "3600"
            ],
            "workingDirectory": [
                ""
            ]
        },
        "InstanceIds": [],
        "Targets": [
            {
                "Key": "InstanceIds",
                "Values": [
                    "i-098b005b6dd83c586"
                ]
            }
        ],
        "RequestedDateTime": "2020-11-23T20:54:17.410000+01:00",
        "Status": "Pending",
        "StatusDetails": "Pending",
        "OutputS3BucketName": "",
        "OutputS3KeyPrefix": "",
        "MaxConcurrency": "50",
        "MaxErrors": "0",
        "TargetCount": 0,
        "CompletedCount": 0,
        "ErrorCount": 0,
        "DeliveryTimedOutCount": 0,
        "ServiceRole": "",
        "NotificationConfig": {
            "NotificationArn": "",
            "NotificationEvents": [],
            "NotificationType": ""
        },
        "CloudWatchOutputConfig": {
            "CloudWatchLogGroupName": "",
            "CloudWatchOutputEnabled": false
        },
        "TimeoutSeconds": 600
    }
}