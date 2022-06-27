# Variabel-variabel
FILE_JAR=target/MultiMapper-1.0-SNAPSHOT.jar
HADOOP_USERNAME=h-user
IP_NAMENODE=192.168.0.12
HOME_DIR=/home/h-user/
NAMA_JAR_TUJUAN=multiMapper.jar
PACKAGE_ID=org.doyatama.MultipleMapperReducer
INPUT_FOLDER_DESC=/DitoCahyaPratama/Input/Desc
INPUT_FOLDER_TEMPERATURE=/DitoCahyaPratama/Input/Temperature
OUTPUT_FOLDER=/DitoCahyaPratama/Hasil

# Bersihkan console
clear

# Jalankan SCP
SCP_ARG="${HADOOP_USERNAME}@${IP_NAMENODE}:${HOME_DIR}${NAMA_JAR_TUJUAN}"
echo "Running SCP.."
echo "${SCP_ARG}"
scp $FILE_JAR $SCP_ARG

# SSH ke Name Node dan jalankan MapReduce job
echo "Connecting to Namenode and execute MapReduce Job.."
HADOOP_JAR_COMMAND="hadoop jar ${NAMA_JAR_TUJUAN} ${PACKAGE_ID} ${INPUT_FOLDER_DESC} ${INPUT_FOLDER_TEMPERATURE} ${OUTPUT_FOLDER}"
LS_OUTPUT_COMMAND="hadoop fs -ls ${OUTPUT_FOLDER}"
CAT_OUTPUT_COMMAND="hadoop fs -cat ${OUTPUT_FOLDER}/part-r-00000"
ssh "${HADOOP_USERNAME}@${IP_NAMENODE}" "${HADOOP_JAR_COMMAND}; ${LS_OUTPUT_COMMAND}; ${CAT_OUTPUT_COMMAND}; exit"
echo "Selesai."